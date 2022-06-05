package com.best.carsalesapi.controller;

import com.best.carsalesapi.controller.model.CreateCarRequestModel;
import com.best.carsalesapi.controller.model.UpdateCarRequestModel;
import com.best.carsalesapi.controller.validator.ValueOfEnum;
import com.best.carsalesapi.entity.Car;
import com.best.carsalesapi.entity.model.CarAvailabilityStatus;
import com.best.carsalesapi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/car")
// to apply customized validations to the request params/body
@Validated
public class CarController {
    @Autowired
    CarService carService;

    CarController(CarService carService) {
        this.carService = carService;
    }

    // todo, pagination 
    // todo, audit
    // todo, role control
    // todo, api documentation 
    // todo, logging
    @GetMapping("/listCarsByCarAvailabilityStatus/{status}")
    public ResponseEntity<Collection<Car>> listCarsByCarAvailabilityStatus(
            @PathVariable("status")
            @Valid @ValueOfEnum(enumClass = CarAvailabilityStatus.class) String status) {
        return new ResponseEntity<>(
                carService.listCarsByCarAvailabilityStatus(status), HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<Car> addNewCarToInventory(
            @RequestBody @Valid CreateCarRequestModel input) {
        return new ResponseEntity<>(
                carService.createNewCar(input),
                HttpStatus.CREATED
        );
    }

    /**
     * Update entire car information
     *
     * @param input {@link UpdateCarRequestModel}
     * @return
     */
    @PutMapping("")
    public ResponseEntity<Car> updateCar(@RequestBody @Valid UpdateCarRequestModel input) {
        return new ResponseEntity<>(
                carService.updateCar(input),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable("id") Long id) {
        Car car = carService.deleteCar(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}

