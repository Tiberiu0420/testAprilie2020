package test.aprilie.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.aprilie.persistence.entity.CarDriverHistory;
import test.aprilie.persistence.entity.Driver;

import java.util.List;

@Repository
public interface CarDriverHistoryRepository extends JpaRepository<CarDriverHistory, Long> {
    List<CarDriverHistory> findByDriverId(Long driverId);
}
