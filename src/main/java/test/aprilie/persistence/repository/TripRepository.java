package test.aprilie.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.aprilie.persistence.entity.Driver;
import test.aprilie.persistence.entity.Trip;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDriverNull();

    List<Trip> findByDriverId(Long driverId);

    @Query("SELECT AVG(t.rating) FROM Trip t WHERE t.driver.id=?1")
    Float getAverageRatingForDriver(Long driverId);
}
