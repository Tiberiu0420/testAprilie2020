package test.aprilie.service;

public interface PaymentSimulatorService {
    void simulatePayment(Long paymentId, String callbackURL);
}
