package com.kotiswar.travel.repository;

import com.kotiswar.travel.entitiy.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long> {
}
