package com.kotiswar.travel.controllers;
import com.kotiswar.travel.entitiy.Trip;
import com.kotiswar.travel.payloads.TripDto;
import com.kotiswar.travel.services.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;
    private final ModelMapper modelMapper;

    @Autowired
    public TripController(TripService tripService, ModelMapper modelMapper) {
        this.tripService = tripService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<TripDto>> getAllTrips() {
        List<TripDto> trips = tripService.getAllTrips().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long id) {
        return tripService.getTripById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto) {
        Trip trip = convertToEntity(tripDto);
        Trip createdTrip = tripService.createTrip(trip);
        return new ResponseEntity<>(convertToDto(createdTrip), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Long id, @RequestBody TripDto tripDto) {
        Trip trip = convertToEntity(tripDto);
        Trip updatedTrip = tripService.updateTrip(id, trip);
        return ResponseEntity.ok(convertToDto(updatedTrip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    // Method to convert Trip entity to TripDto
    private TripDto convertToDto(Trip trip) {
        return modelMapper.map(trip, TripDto.class);
    }

    // Method to convert TripDto to Trip entity
    private Trip convertToEntity(TripDto tripDto) {
        return modelMapper.map(tripDto, Trip.class);
    }
}

