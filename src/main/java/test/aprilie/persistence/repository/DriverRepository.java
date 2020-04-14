package test.aprilie.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.aprilie.persistence.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
