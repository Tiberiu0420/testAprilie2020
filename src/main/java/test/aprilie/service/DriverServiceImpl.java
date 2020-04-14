package test.aprilie.service;

import org.springframework.stereotype.Service;
import test.aprilie.exception.TripAlreadyAssignedException;
import test.aprilie.persistence.entity.Driver;
import test.aprilie.persistence.entity.Trip;
import test.aprilie.persistence.repository.DriverRepository;
import test.aprilie.persistence.repository.TripRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;

    public DriverServiceImpl(TripRepository tripRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriver(Long id) {
        return driverRepository.getOne(id);
    }

    @Override
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
