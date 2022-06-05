package com.best.carsalesapi.controller.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorResponse {
    private Date timestamp;
    private String message;
    private String description;
}
