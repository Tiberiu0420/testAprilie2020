package test.aprilie.rest.model;

import java.util.List;

public class DriverProfileUI {
    private DriverUI driver;
    private CarUI currentCar;
    private List<CarDriverHistoryUI> carHistory;
    private Float averageRating;
    private Float averageTripPrice;

    public DriverProfileUI(DriverUI driver, CarUI currentCar, List<CarDriverHistoryUI> carHistory,
                           Float averageRating, Float averageTripPrice) {
        this.driver = driver;
        this.currentCar = currentCar;
        this.carHistory = carHistory;
        this.averageRating = averageRating;
        this.averageTripPrice = averageTripPrice;
    }

    public DriverUI getDriver() {
        return driver;
    }

    public CarUI getCurrentCar() {
        return currentCar;
    }

    public List<CarDriverHistoryUI> getCarHistory() {
        return carHistory;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public Float getAverageTripPrice() {
        return averageTripPrice;
    }
}
