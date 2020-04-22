package test.aprilie.service;

import test.aprilie.persistence.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getPaymentsForDriver(Long driverId);

    Long createPreAuthorizePayment(Long tripId, Float price, String callbackURL);

    Long createConfirmationPayment(Long tripId, Float price, Float reward, String callbackURL);

    Payment getPayment(Long id);
}
