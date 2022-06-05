package com.best.carsalesapi.service;

import com.best.carsalesapi.entity.Car;
import com.best.carsalesapi.entity.UserEntity;
import com.best.carsalesapi.entity.model.CarAvailabilityStatus;
import com.best.carsalesapi.entity.model.UserRoleEnum;
import com.best.carsalesapi.repository.CarRepository;
import com.best.carsalesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MockDataGeneratorService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public void generateCars() {
        Collection<Car> cars = new ArrayList<>();
        Car car1 = Car.builder().brandName("x").price(50.0).build();
        Car car2 = Car.builder().brandName("y").price(20.0).build();
        Car car3 = Car.builder()
                .brandName("y")
                .price(20.0)
                .carAvailabilityStatus(CarAvailabilityStatus.SOLD)
                .build();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        carRepository.saveAll(cars);
    }

    public void generateUsers() {
        Collection<UserEntity> userEntities = new ArrayList<>();

        UserEntity user1 = UserEntity.builder()
                .name("leo_user_1")
                .build();

        UserEntity user2 = UserEntity.builder()
                .name("leo_user_2")
                .build();

        UserEntity user3 = UserEntity.builder()
                .name("leo_admin_1")
                .role(UserRoleEnum.ADMIN)
                .build();

        userEntities.add(user1);
        userEntities.add(user2);
        userEntities.add(user3);

        userRepository.saveAll(userEntities);
    }
}

