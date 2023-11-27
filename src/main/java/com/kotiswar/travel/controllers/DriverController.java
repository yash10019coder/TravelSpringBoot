package com.kotiswar.travel.controllers;

import com.kotiswar.travel.entitiy.Driver;
import com.kotiswar.travel.payloads.DriverDto;
import com.kotiswar.travel.services.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    private final DriverService driverService;
    private final ModelMapper modelMapper;

    @Autowired
    public DriverController(DriverService driverService, ModelMapper modelMapper) {
        this.driverService = driverService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<DriverDto>> getAllDrivers() {
        List<DriverDto> drivers = driverService.getAllDrivers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DriverDto> createDriver(@RequestBody DriverDto driverDto) {
        Driver driver = convertToEntity(driverDto);
        Driver createdDriver = driverService.createDriver(driver);
        return new ResponseEntity<>(convertToDto(createdDriver), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable Long id, @RequestBody DriverDto driverDto) {
        Driver driver = convertToEntity(driverDto);
        Driver updatedDriver = driverService.updateDriver(id, driver);
        return ResponseEntity.ok(convertToDto(updatedDriver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    // Method to convert Driver entity to DriverDto
    private DriverDto convertToDto(Driver driver) {
        return modelMapper.map(driver, DriverDto.class);
    }

    // Method to convert DriverDto to Driver entity
    private Driver convertToEntity(DriverDto driverDto) {
        return modelMapper.map(driverDto, Driver.class);
    }
}
