package com.kotiswar.travel.services;

import com.kotiswar.travel.entitiy.Trip;
import com.kotiswar.travel.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    //TODO: Use DTO instead of entity

    private final TripRepo tripRepository;

    @Autowired
    public TripService(TripRepo tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public Trip createTrip(Trip trip) {
        // Add validation or business logic here
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    public Trip updateTrip(Long id, Trip updatedTrip) {
        return tripRepository.findById(id)
                .map(trip -> {
                    trip.setDriver(updatedTrip.getDriver());
                    trip.setStartTime(updatedTrip.getStartTime());
                    trip.setEndTime(updatedTrip.getEndTime());
                    return tripRepository.save(trip);
                })
                .orElseGet(() -> {
                    updatedTrip.setId(id);
                    return tripRepository.save(updatedTrip);
                });
    }

    // Additional methods and business logic can be added here
}
