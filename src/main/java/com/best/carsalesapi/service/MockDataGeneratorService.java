package com.best.carsalesapi.service;

import com.best.carsalesapi.entity.Car;
import com.best.carsalesapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MockDataGeneratorService {

    @Autowired
    private CarRepository carRepository;

    public void generateCars() {
        Collection<Car> cars = new ArrayList<>();
        Car car1 = Car.builder().brandName("x").price(50.0).build();
        Car car2 = Car.builder().brandName("y").price(20.0).build();
        cars.add(car1);
        cars.add(car2);
        carRepository.saveAll(cars);
    }
}

