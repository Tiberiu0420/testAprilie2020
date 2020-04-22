package test.aprilie.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import test.aprilie.persistence.entity.Payment;
import test.aprilie.rest.model.NewPaymentUI;
import test.aprilie.rest.model.PaymentUI;
import test.aprilie.service.PaymentService;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/payments")
@Transactional
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Long> createPayment(@RequestBody NewPaymentUI newPaymentUI) {
        Long paymentId;
        switch (newPaymentUI.getPaymentType()) {
            case PRE_AUTHORIZATION:
                paymentId = paymentService.createPreAuthorizePayment(newPaymentUI.getTripId(), newPaymentUI.getPrice(),
                        newPaymentUI.getCallbackURL());
                return ResponseEntity.accepted().body(paymentId);
            case CONFIRMATION:
                paymentId = paymentService.createConfirmationPayment(newPaymentUI.getTripId(), newPaymentUI.getPrice(),
                        newPaymentUI.getReward(), newPaymentUI.getCallbackURL());
                return ResponseEntity.accepted().body(paymentId);
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentUI> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(mapPaymentToUI(paymentService.getPayment(id)));
    }

    private PaymentUI mapPaymentToUI(Payment payment) {
        return new ModelMapper().map(payment, PaymentUI.class);
    }
}
