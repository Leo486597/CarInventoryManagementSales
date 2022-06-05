package com.best.carsalesapi.service;

import com.best.carsalesapi.controller.model.CreateCarRequestModel;
import com.best.carsalesapi.controller.model.UpdateCarRequestModel;
import com.best.carsalesapi.entity.Car;
import com.best.carsalesapi.entity.model.CarAvailabilityStatus;
import com.best.carsalesapi.exception.NotFoundException;
import com.best.carsalesapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public Collection<Car> listCarsByCarAvailabilityStatus(String status) {
        return carRepository.findCarsByCarAvailabilityStatus(
                CarAvailabilityStatus.valueOf(status)
        );
    }

    public Car createNewCar(CreateCarRequestModel model) {
        Car car = Car.builder()
                .brandName(model.getBrandName())
                .price(model.getPrice())
                .build();
        return carRepository.save(car);
    }

    public Car updateCar(UpdateCarRequestModel model) {
        Car car = carRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException("Resource Not Found"));
        car.setBrandName(model.getBrandName());
        car.setCarAvailabilityStatus(CarAvailabilityStatus.valueOf(model.getCarAvailabilityStatus()));
        car.setPrice(model.getPrice());

        return carRepository.save(car);
    }
}
