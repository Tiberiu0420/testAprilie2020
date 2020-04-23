package test.aprilie.service;

import org.springframework.stereotype.Service;
import test.aprilie.exception.TripAlreadyAssignedException;
import test.aprilie.persistence.entity.Driver;
import test.aprilie.persistence.entity.Trip;
import test.aprilie.persistence.repository.DriverRepository;
import test.aprilie.persistence.repository.TripRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;

    public TripServiceImpl(TripRepository tripRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Trip createTrip(Trip newTrip) {
        return tripRepository.save(newTrip);
    }

    @Override
    public Trip updateTripSetDriver(Long tripId, Long driverId) throws TripAlreadyAssignedException {
        Trip trip = tripRepository.findById(tripId).orElseThrow(EntityNotFoundException::new);

        if (trip.getDriver() != null) {
            throw new TripAlreadyAssignedException();
        }

        Driver driver = driverRepository.findById(driverId).orElseThrow(EntityNotFoundException::new);
        trip.setDriver(driver);

        return tripRepository.save(trip);
    }

    @Override
    public Trip rateTrip(Long tripId, short stars) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(EntityNotFoundException::new);
        trip.setRating(stars);

        return tripRepository.save(trip);
    }

    @Override
    public Trip getTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Trip> getUnassignedTrips() {
        return tripRepository.findByDriverNull();
    }

    @Override
    public List<Trip> findByDriverId(Long driverId) {
        return tripRepository.findByDriverId(driverId);
    }

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Float getAverageRatingForDriver(Long driverId) {
        return tripRepository.getAverageRatingForDriver(driverId);
    }
}
