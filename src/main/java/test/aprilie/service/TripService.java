package test.aprilie.service;

import test.aprilie.exception.TripAlreadyAssignedException;
import test.aprilie.persistence.entity.Trip;

import java.util.List;

public interface TripService {
    Trip createTrip(Trip newTrip);

    Trip updateTripSetDriver(Long tripId, Long DriverId) throws TripAlreadyAssignedException;

    Trip rateTrip(Long tripId, short stars);

    Trip getTrip(Long tripId);

    List<Trip> getUnassignedTrips();

    List<Trip> findByDriverId(Long driverId);

    List<Trip> findAll();

    Float getAverageRatingForDriver(Long id);


}
