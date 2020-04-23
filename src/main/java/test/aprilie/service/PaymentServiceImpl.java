package test.aprilie.service;

import org.springframework.stereotype.Service;
import test.aprilie.persistence.entity.Payment;
import test.aprilie.persistence.entity.PaymentStatus;
import test.aprilie.persistence.repository.PaymentRepository;
import test.aprilie.persistence.repository.TripRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.concurrent.CompletableFuture.runAsync;
import static test.aprilie.persistence.entity.PaymentStatus.PENDING_AUTHORIZATION;
import static test.aprilie.persistence.entity.PaymentStatus.PENDING_CONFIRMATION;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final TripRepository tripRepository;
    private final PaymentSimulatorService paymentSimulatorService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, TripRepository tripRepository,
                              PaymentSimulatorService paymentSimulatorService) {
        this.paymentRepository = paymentRepository;
        this.tripRepository = tripRepository;
        this.paymentSimulatorService = paymentSimulatorService;
    }

    @Override
    public List<Payment> getPaymentsForDriver(Long driverId) {
        return paymentRepository.findByTripDriverId(driverId);
    }

    @Override
    public Long createPreAuthorizePayment(Long tripId, Float price, String callbackURL) {
        return createPayment(tripId, price, 0F, PENDING_AUTHORIZATION, callbackURL);
    }

    @Override
    public Long createConfirmationPayment(Long tripId, Float price, Float reward, String callbackURL) {
        return createPayment(tripId, price, reward, PENDING_CONFIRMATION, callbackURL);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Float getAveragePriceForDriver(Long driverId) {
        return paymentRepository.getAveragePriceForDriver(driverId);
    }

    private Long createPayment(Long tripId, Float price, Float reward, PaymentStatus paymentStatus, String callbackURL) {
        Payment payment = new Payment();
        payment.setPrice(price);
        payment.setStart(LocalDateTime.now());
        payment.setStatus(paymentStatus);
        payment.setReward(reward);
        payment.setTrip(tripRepository.findById(tripId).orElseThrow(EntityNotFoundException::new));
        payment = paymentRepository.save(payment);

        Long paymentId = payment.getId();
        runAsync(() -> paymentSimulatorService.simulatePayment(paymentId, callbackURL));
        return paymentId;
    }
}
