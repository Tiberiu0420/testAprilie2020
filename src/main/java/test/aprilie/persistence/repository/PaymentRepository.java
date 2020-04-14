package test.aprilie.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.aprilie.persistence.entity.Driver;
import test.aprilie.persistence.entity.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTripDriverId(Long driverId);
}
