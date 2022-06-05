package com.best.carsalesapi.controller;

import com.best.carsalesapi.controller.model.CreateCarRequestModel;
import com.best.carsalesapi.entity.Car;
import com.best.carsalesapi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    CarService carService;

    CarController(CarService carService) {
        this.carService = carService;
    }

    // todo, pagination 
    // todo, error handling
    // todo, audit
    // todo, role control
    // todo, input validation
    // todo, api documentation 
    // todo, logging
    @GetMapping("/listCarsByCarAvailabilityStatus/{status}")
    public Collection<Car> listCarsByCarAvailabilityStatus(@PathVariable("status") String status) {
        return carService.listCarsByCarAvailabilityStatus(status);
    }

    @PostMapping("/")
    public Car addNewCarToInventory(@RequestBody CreateCarRequestModel input) {
        return carService.createNewCar(input);
    }
}

