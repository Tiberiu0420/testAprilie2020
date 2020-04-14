package test.aprilie.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "car_driver_history")
public class CarDriverHistory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    private LocalDateTime startAllocTime;

    private LocalDateTime endAllocTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
