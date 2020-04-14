package test.aprilie.service;

import test.aprilie.persistence.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getPaymentsForDriver(Long driverId);

    Payment getPayment(Long id);

    Payment createPayment(Payment payment);
}
