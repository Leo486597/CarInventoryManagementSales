package com.best.carsalesapi.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collection;

@Data
public class CreateOrderRequestModel {
    @NotNull
    Long SellerId;
    
    @NotNull
    Long buyerId;
    
    @NotNull
    Collection<Long> carsId;
    
    @NotNull
    @Positive
    Double price;
}
