package test.aprilie.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import test.aprilie.exception.TripAlreadyAssignedException;
import test.aprilie.persistence.entity.Trip;
import test.aprilie.rest.model.RatingUI;
import test.aprilie.rest.model.TripUI;
import test.aprilie.service.TripService;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/trips")
@Transactional
public class TripController {
    private TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripUI>> getTrips(@RequestParam("unassigned") Boolean unassigned) {
        List<Trip> trips = tripService.getTrips(unassigned);
        return ResponseEntity.ok(trips.stream().map(this::mapTripToUI).collect(toList()));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripUI> createTrip(@RequestBody TripUI newTripUI) {
        Trip createdTrip = tripService.createTrip(mapTripUIToEntity(newTripUI));
        return ResponseEntity.status(CREATED)
                .body(mapTripToUI(createdTrip));
    }

    @PatchMapping(value = "/{tripId}/rate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripUI> rateTrip(@PathVariable Long tripId, @RequestBody RatingUI rating) {
        return ResponseEntity.ok(mapTripToUI(tripService.rateTrip(tripId, rating.getStars())));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripUI> getTrip(@PathVariable Long id) {
        return ResponseEntity.ok(mapTripToUI(tripService.getTrip(id)));
    }

    @PatchMapping(value = "trips/{tripId}/drivers/{driverId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripUI> acceptTrip(@PathVariable Long tripId, @PathVariable Long driverId) {
        try {
            return ResponseEntity.ok(mapTripToUI(tripService.acceptTrip(tripId, driverId)));
        } catch (TripAlreadyAssignedException e) {
            return ResponseEntity.status(CONFLICT).build();
        }
    }

    private TripUI mapTripToUI(Trip trip) {
        return new ModelMapper().map(trip, TripUI.class);
    }

    private Trip mapTripUIToEntity(TripUI tripUI) {
        return new ModelMapper().map(tripUI, Trip.class);
    }
}
