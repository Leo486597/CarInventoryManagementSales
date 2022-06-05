package com.best.carsalesapi.exception;

import com.best.carsalesapi.controller.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constrainViolateExceptionHandler(ConstraintViolationException exception, WebRequest request) {
        StringBuilder error = new StringBuilder();

        for (ConstraintViolation violation : exception.getConstraintViolations()) {
            error.append(String.format("%s: %s", violation.getPropertyPath().toString(), violation.getMessage()));
        }

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(new Date())
                .message(error.toString())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ApiHandledException.class})
    public ResponseEntity<ErrorResponse> apiHandledException(ApiHandledException exception, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> invalidArgumentExceptionHandler(Exception exception, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundExceptionHandler(Exception exception, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> unauthorizedExceptionHandler(Exception exception, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
