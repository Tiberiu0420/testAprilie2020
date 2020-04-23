package test.aprilie.service;

import test.aprilie.persistence.entity.CarDriverHistory;

import java.util.List;

public interface CarDriverHistoryService {
    List<CarDriverHistory> getByDriver(Long id);
}
