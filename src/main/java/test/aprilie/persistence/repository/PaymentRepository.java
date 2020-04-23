package test.aprilie.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.aprilie.persistence.entity.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTripDriverId(Long driverId);

    @Query("SELECT AVG(p.price) FROM Payment p WHERE p.trip.driver.id=?1")
    Float getAveragePriceForDriver(Long driverId);
}
