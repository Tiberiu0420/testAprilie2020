package test.aprilie.service;

import test.aprilie.exception.TripAlreadyAssignedException;
import test.aprilie.persistence.entity.Trip;

import java.util.List;

public interface TripService {
    Trip createTrip(Trip newTrip);

    Trip acceptTrip(Long tripId, Long DriverId) throws TripAlreadyAssignedException;

    Trip rateTrip(Long tripId, short stars);

    Trip getTrip(Long tripId);

    List<Trip> getTrips(Boolean unassigned);
}
