package test.aprilie.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import test.aprilie.persistence.entity.Car;
import test.aprilie.persistence.entity.CarDriverHistory;
import test.aprilie.persistence.entity.Driver;
import test.aprilie.persistence.entity.Payment;
import test.aprilie.rest.model.*;
import test.aprilie.service.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/drivers")
@Transactional
public class DriverController {
    private final DriverService driverService;
    private final PaymentService paymentService;
    private final CarService carService;
    private final TripService tripService;
    private final CarDriverHistoryService carDriverHistoryService;

    public DriverController(DriverService driverService, PaymentService paymentService, TripService tripService,
                            CarService carService, CarDriverHistoryService carDriverHistoryService) {
        this.driverService = driverService;
        this.paymentService = paymentService;
        this.carService = carService;
        this.tripService = tripService;
        this.carDriverHistoryService = carDriverHistoryService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriverProfileUI>> getDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers.stream().map(this::getDriverProfileUI).collect(toList()));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverUI> createDriver(@RequestBody DriverUI newDriverUI) {
        Driver createdDriver = driverService.createDriver(mapDriverUIToEntity(newDriverUI));
        return ResponseEntity.status(CREATED)
                .body(mapDriverToUI(createdDriver));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverProfileUI> getDriver(@PathVariable Long id) {
        Driver driver = driverService.getDriver(id);
        return ResponseEntity.ok(getDriverProfileUI(driver));
    }

    private DriverProfileUI getDriverProfileUI(Driver driver) {
        DriverUI driverUI = mapDriverToUI(driver);
        CarUI currentCar = carService.getByDriverId(driver.getId()).map(this::mapCarToUI).orElse(null);
        List<CarDriverHistoryUI> carHistory = carDriverHistoryService.getByDriver(driver.getId())
                .stream().map(this::mapCarDriverHistoryToUI).collect(toList());
        Float averageRating = tripService.getAverageRatingForDriver(driver.getId());
        Float averageTripPrice = paymentService.getAveragePriceForDriver(driver.getId());

        return new DriverProfileUI(driverUI, currentCar, carHistory, averageRating, averageTripPrice);
    }

    @GetMapping(value = "/{id}/payments", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NewPaymentUI>> getPayments(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentsForDriver(id)
                .stream().map(this::mapPaymentToUI).collect(toList()));
    }

    private NewPaymentUI mapPaymentToUI(Payment payment) {
        return new ModelMapper().map(payment, NewPaymentUI.class);
    }

    private DriverUI mapDriverToUI(Driver driver) {
        return new ModelMapper().map(driver, DriverUI.class);
    }

    private Driver mapDriverUIToEntity(DriverUI driverUI) {
        return new ModelMapper().map(driverUI, Driver.class);
    }

    private CarUI mapCarToUI(Car car) {
        return new ModelMapper().map(car, CarUI.class);
    }

    private CarDriverHistoryUI mapCarDriverHistoryToUI(CarDriverHistory carDriverHistory) {
        return new ModelMapper().map(carDriverHistory, CarDriverHistoryUI.class);
    }
}
