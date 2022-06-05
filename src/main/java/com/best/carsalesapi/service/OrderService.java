package com.best.carsalesapi.service;

import com.best.carsalesapi.controller.model.CreateOrderRequestModel;
import com.best.carsalesapi.entity.Car;
import com.best.carsalesapi.entity.Order;
import com.best.carsalesapi.entity.UserEntity;
import com.best.carsalesapi.entity.model.CarAvailabilityStatus;
import com.best.carsalesapi.entity.model.CarOrderStatus;
import com.best.carsalesapi.exception.ApiHandledException;
import com.best.carsalesapi.exception.NotFoundException;
import com.best.carsalesapi.repository.CarRepository;
import com.best.carsalesapi.repository.OrderRepository;
import com.best.carsalesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    CarService carService;

    public OrderService(CarService carService) {
        this.carService = carService;
    }

    @Transactional
    public Order createNewOrder(CreateOrderRequestModel model) {
        Optional<UserEntity> buyer = userRepository.findById(model.getBuyerId());
        Optional<UserEntity> seller = userRepository.findById(model.getSellerId());

        if (!buyer.isPresent() || !seller.isPresent())
            throw new NotFoundException("Buyer or seller is Not found");

        UserEntity validBuyer = buyer.get();
        UserEntity validSeller = seller.get();
        Collection<Car> cars = new ArrayList<>();

        // validate car's availability
        model.getCarsId().forEach(carId -> {
            Car car = carRepository
                    .findById(carId)
                    .orElseThrow(() -> new NotFoundException("Car not found"));

            if (car.getCarAvailabilityStatus() != CarAvailabilityStatus.OPEN) {
                throw new ApiHandledException("Car not available");
            }

            cars.add(car);
            car.setCarAvailabilityStatus(CarAvailabilityStatus.SOLD);
        });

        Order order = Order.builder()
                .buyer(validBuyer)
                .seller(validSeller)
                .orderedItems(cars)
                .orderStatus(CarOrderStatus.RESOLVED)
                .build();
        return orderRepository.save(order);
    }
}

