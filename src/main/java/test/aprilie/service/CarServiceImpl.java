package test.aprilie.service;

import org.springframework.stereotype.Service;
import test.aprilie.persistence.entity.Car;
import test.aprilie.persistence.entity.Driver;
import test.aprilie.persistence.repository.CarRepository;
import test.aprilie.persistence.repository.DriverRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    public CarServiceImpl(CarRepository carRepository, DriverRepository driverRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Car registerCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car assignDriver(Long carId, Long driverId) {
        Car car = carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
        Driver driver = driverRepository.findById(driverId).orElseThrow(EntityNotFoundException::new);

        car.setDriver(driver);
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> getByDriverId(Long driverId) {
        return carRepository.findByDriverId(driverId);
    }
}
