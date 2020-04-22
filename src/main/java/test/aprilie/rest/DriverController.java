package test.aprilie.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import test.aprilie.persistence.entity.Driver;
import test.aprilie.persistence.entity.Payment;
import test.aprilie.rest.model.DriverUI;
import test.aprilie.rest.model.NewPaymentUI;
import test.aprilie.service.DriverService;
import test.aprilie.service.PaymentService;

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

    public DriverController(DriverService driverService, PaymentService paymentService) {
        this.driverService = driverService;
        this.paymentService = paymentService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriverUI>> getDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers.stream().map(this::mapDriverToUI).collect(toList()));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverUI> createDriver(@RequestBody DriverUI newDriverUI) {
        Driver createdDriver = driverService.createDriver(mapDriverUIToEntity(newDriverUI));
        return ResponseEntity.status(CREATED)
                .body(mapDriverToUI(createdDriver));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverUI> getDriver(@PathVariable Long id) {
        return ResponseEntity.ok(mapDriverToUI(driverService.getDriver(id)));
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
}
