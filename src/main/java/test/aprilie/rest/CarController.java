package test.aprilie.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import test.aprilie.persistence.entity.Car;
import test.aprilie.rest.model.CarUI;
import test.aprilie.service.CarService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/cars")
@Transactional
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CarUI> registerCar(@RequestBody CarUI carUI) {
        Car createdCar = carService.registerCar(mapCarUIToEntity(carUI));
        return ResponseEntity.status(CREATED)
                .body(mapCarToUI(createdCar));
    }

    @PatchMapping(value = "/{carId}/assignDriver", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CarUI> assignDriver(@PathVariable Long carId, @RequestParam("driverId") Long driverId) {
        Car updatedCar = carService.assignDriver(carId, driverId);
        return ResponseEntity.ok(mapCarToUI(updatedCar));
    }

    private CarUI mapCarToUI(Car trip) {
        return new ModelMapper().map(trip, CarUI.class);
    }

    private Car mapCarUIToEntity(CarUI tripUI) {
        return new ModelMapper().map(tripUI, Car.class);
    }
}
