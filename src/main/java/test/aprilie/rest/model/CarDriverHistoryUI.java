package test.aprilie.rest.model;

import java.time.LocalDateTime;

public class CarDriverHistoryUI {
    private Long driverId;
    private Long carId;
    private LocalDateTime startAllocTime;
    private LocalDateTime endAllocTime;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public LocalDateTime getStartAllocTime() {
        return startAllocTime;
    }

    public void setStartAllocTime(LocalDateTime startAllocTime) {
        this.startAllocTime = startAllocTime;
    }

    public LocalDateTime getEndAllocTime() {
        return endAllocTime;
    }

    public void setEndAllocTime(LocalDateTime endAllocTime) {
        this.endAllocTime = endAllocTime;
    }
}
