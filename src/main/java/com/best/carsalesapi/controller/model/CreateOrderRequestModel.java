package com.best.carsalesapi.controller.model;

import lombok.Data;

@Data
public class CreateOrderRequestModel {
    Long SellerId;
    Long buyerId;
    Long carId;
    Double price;
}
