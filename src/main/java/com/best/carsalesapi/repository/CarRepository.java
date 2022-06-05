package com.best.carsalesapi.repository;

import com.best.carsalesapi.entity.Car;
import com.best.carsalesapi.entity.model.CarAvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Collection<Car> findCarsByCarAvailabilityStatus(CarAvailabilityStatus status);
}

