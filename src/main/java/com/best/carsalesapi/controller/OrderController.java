package com.best.carsalesapi.controller;

import com.best.carsalesapi.controller.model.CreateOrderRequestModel;
import com.best.carsalesapi.entity.Order;
import com.best.carsalesapi.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/order")
@Validated
public class OrderController {
    OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public Order createNewOrder(@RequestBody @Valid CreateOrderRequestModel input) {
        return orderService.createNewOrder(input);
    }
}
