package com.best.carsalesapi.entity;

import com.best.carsalesapi.entity.model.CarAvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    private String brandName;
    
    private Double price;
    
    @Builder.Default
    private CarAvailabilityStatus carAvailabilityStatus = CarAvailabilityStatus.OPEN;

    // constrains
}


