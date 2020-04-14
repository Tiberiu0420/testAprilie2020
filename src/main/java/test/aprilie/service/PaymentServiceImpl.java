package test.aprilie.service;

import org.springframework.stereotype.Service;
import test.aprilie.persistence.entity.Payment;
import test.aprilie.persistence.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getPaymentsForDriver(Long driverId) {
        return paymentRepository.findByTripDriverId(driverId);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentRepository.getOne(id);
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
