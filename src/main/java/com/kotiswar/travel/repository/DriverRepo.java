package com.kotiswar.travel.repository;

import com.kotiswar.travel.entitiy.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Long> {
}
