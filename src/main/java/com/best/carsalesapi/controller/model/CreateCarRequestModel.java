package com.best.carsalesapi.controller.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class CreateCarRequestModel {

    @NotEmpty
    private String brandName;

    @NotNull
    @Positive
    private Double price;
}

