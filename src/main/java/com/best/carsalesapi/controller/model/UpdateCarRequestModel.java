package com.best.carsalesapi.controller.model;

import com.best.carsalesapi.controller.validator.ValueOfEnum;
import com.best.carsalesapi.entity.model.CarAvailabilityStatus;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;


@Data
@Builder
public class UpdateCarRequestModel {
    @NotNull
    private Long id;

    @NotNull
    private String brandName;
    
    @Positive
    private Double price;
    
    @ValueOfEnum(enumClass = CarAvailabilityStatus.class)
    private String carAvailabilityStatus;
}
