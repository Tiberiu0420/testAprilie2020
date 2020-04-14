package test.aprilie.service;

import org.springframework.stereotype.Service;
import test.aprilie.persistence.entity.Car;
import test.aprilie.persistence.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public Car registerCar(Car car) {
        return carRepository.save(car);
    }
}
