package test.aprilie.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.aprilie.persistence.entity.Car;
import test.aprilie.persistence.entity.Driver;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
   Optional<Car> findByDriverId(Long driverId);
}
