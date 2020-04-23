package test.aprilie.service;

import org.springframework.stereotype.Service;
import test.aprilie.persistence.entity.CarDriverHistory;
import test.aprilie.persistence.repository.CarDriverHistoryRepository;

import java.util.List;

@Service
public class CarDriverHistoryServiceImpl implements CarDriverHistoryService {
    private CarDriverHistoryRepository carDriverHistoryRepository;

    public CarDriverHistoryServiceImpl(CarDriverHistoryRepository carDriverHistoryRepository) {
        this.carDriverHistoryRepository = carDriverHistoryRepository;
    }

    @Override
    public List<CarDriverHistory> getByDriver(Long driverId) {
        return carDriverHistoryRepository.findByDriverId(driverId);
    }
}
