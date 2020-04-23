package test.aprilie.service;

import test.aprilie.persistence.entity.Car;

import java.util.Optional;

public interface CarService {
    Car registerCar(Car car);

    Car assignDriver(Long carId, Long driverId);

    Optional<Car> getByDriverId(Long driverId);
}
