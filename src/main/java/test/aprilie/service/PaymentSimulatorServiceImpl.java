package test.aprilie.service;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import test.aprilie.persistence.entity.Payment;
import test.aprilie.persistence.entity.PaymentStatus;
import test.aprilie.persistence.repository.PaymentRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class PaymentSimulatorServiceImpl implements PaymentSimulatorService {
    private static final int PAYMENT_DELAY = 2_000;

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    public PaymentSimulatorServiceImpl(PaymentRepository paymentRepository, RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public void simulatePayment(Long paymentId, String callbackURL) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(EntityNotFoundException::new);
        try {
            Thread.sleep(PAYMENT_DELAY);
            payment.setStatus(PaymentStatus.SUCCEEDED);
            payment.setEnd(LocalDateTime.now());
        } catch (InterruptedException e) {
            payment.setStatus(PaymentStatus.FAILED);
        }
        paymentRepository.save(payment);
        notifyClient(payment.getTrip().getId(), paymentId, payment.getStatus(), callbackURL);
    }

    private void notifyClient(Long tripId, Long paymentId, PaymentStatus paymentStatus, String callbackURL) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject clientNotificationObject = new JSONObject();
            clientNotificationObject.put("tripId", tripId);
            clientNotificationObject.put("paymentId", paymentId);
            clientNotificationObject.put("paymentStatus", paymentStatus);

            HttpEntity<String> request = new HttpEntity<>(clientNotificationObject.toString(), headers);
            restTemplate.postForObject(callbackURL, request, String.class);
        } catch (Exception e) {
            //TODO handle exception
            e.printStackTrace();
        }
    }
}
