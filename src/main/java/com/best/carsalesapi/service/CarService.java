package com.best.carsalesapi.service;

import com.best.carsalesapi.controller.model.CreateCarRequestModel;
import com.best.carsalesapi.entity.Car;
import com.best.carsalesapi.entity.model.CarAvailabilityStatus;
import com.best.carsalesapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;
    
    public Collection<Car> listCarsByCarAvailabilityStatus(String status){
        CarAvailabilityStatus statusEnum = CarAvailabilityStatus.valueOf(status.toUpperCase());
        return carRepository.findCarsByCarAvailabilityStatus(statusEnum);
    }
    
    public Car createNewCar(CreateCarRequestModel model){
        Car car = Car.builder()
                .brandName(model.getBrandName())
                .price(model.getPrice())
                .build();
        return carRepository.save(car);
    }
}
