package com.kotiswar.travel.services;

import com.kotiswar.travel.entitiy.Driver;
import com.kotiswar.travel.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepo driverRepository;

    @Autowired
    public DriverService(DriverRepo driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    public Driver createDriver(Driver driver) {
        // Insert validation or business logic here
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, Driver updatedDriver) {
        return driverRepository.findById(id)
                .map(driver -> {
                    driver.setName(updatedDriver.getName());
                    driver.setLicenseNumber(updatedDriver.getLicenseNumber());
                    driver.setContactInfo(updatedDriver.getContactInfo());
                    return driverRepository.save(driver);
                })
                .orElseGet(() -> {
                    updatedDriver.setId(id);
                    return driverRepository.save(updatedDriver);
                });
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    // Additional methods and business logic can be implemented as needed
}
