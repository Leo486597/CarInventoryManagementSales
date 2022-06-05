package com.best.carsalesapi.controller.model;

import lombok.Data;

@Data
public class CreateCarRequestModel {
    private String brandName;
    private Double price;
}
