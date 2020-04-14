package test.aprilie.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.aprilie.persistence.entity.Driver;
import test.aprilie.persistence.entity.Trip;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDriverNull();

    List<Trip> findByDriverNotNull();
}
