package test.aprilie.service;

import test.aprilie.persistence.entity.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers();

    Driver getDriver(Long id);

    Driver createDriver(Driver driver);
}
