package test.aprilie.rest.model;

import java.time.LocalDateTime;

public class PaymentUI {
    private LocalDateTime start;
    private LocalDateTime end;

    private String status;
    private Float price;
    private Float reward;

    private TripUI trip;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getReward() {
        return reward;
    }

    public void setReward(Float reward) {
        this.reward = reward;
    }

    public TripUI getTrip() {
        return trip;
    }

    public void setTrip(TripUI trip) {
        this.trip = trip;
    }
}
