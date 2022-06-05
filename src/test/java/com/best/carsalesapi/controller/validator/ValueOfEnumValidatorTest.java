package com.best.carsalesapi.controller.validator;

import com.best.carsalesapi.controller.model.UpdateCarRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

class ValueOfEnumValidatorTest {

    Validator validator;

    @BeforeEach
    void beforeEach(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenAllAcceptable_thenShouldNotGiveConstraintViolations() {
        UpdateCarRequestModel model = UpdateCarRequestModel.builder()
                .price(2.0)
                .id(1L)
                .carAvailabilityStatus("OPEN")
                .brandName("TEST")
                .build();

        Set<ConstraintViolation<UpdateCarRequestModel>> violations = validator.validate(model);
        Assertions.assertEquals(violations.size(), 0);
    }

    @Test
    public void whenStatusNotAcceptable_thenShouldGiveConstraintViolations() {
        UpdateCarRequestModel model = UpdateCarRequestModel.builder()
                .id(1L)
                .price(2.0)
                .carAvailabilityStatus("OPENs")
                .brandName("TEST")
                .build();
        
        Set<ConstraintViolation<UpdateCarRequestModel>> violations = validator.validate(model);
        Assertions.assertEquals(violations.size(), 1);
    }

}