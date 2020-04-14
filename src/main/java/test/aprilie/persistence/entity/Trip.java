package test.aprilie.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private BigDecimal startLocation;

    private BigDecimal endLocation;

    @Min(0)
    @Max(5)
    private Short rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(BigDecimal startLocation) {
        this.startLocation = startLocation;
    }

    public BigDecimal getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(BigDecimal endLocation) {
        this.endLocation = endLocation;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }
}
