package com.best.carsalesapi.controller;

import com.best.carsalesapi.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addNewCarToInventory() throws Exception {
        String uri = "/api/car";

        String json = "{\"brandName\": \"abc\", \"price\": 10.0}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isCreated());
    }

    @Test
    void addNewCarToInventory_whenInputViolateTheConstrain() throws Exception {
        String uri = "/api/car";

        String json = "{\"brandName\": \"abc\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);
                });
    }

    @Test
    void updateCar() throws Exception {
        String uri = "/api/car";
        String json = "{\"id\": 1, \"brandName\": \"abc\", \"price\": 10.0, \"carAvailabilityStatus\": \"SOLD\"}";
        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isOk());
    }

    @Test
    void updateCar_whenIdNotFound() throws Exception {
        String uri = "/api/car";
        String json = "{\"id\": 1000, \"brandName\": \"abc\", \"price\": 10.0, \"carAvailabilityStatus\": \"SOLD\"}";
        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof NotFoundException)
                );
    }

    @Test
    void updateCar_whenInvalidInput() throws Exception {
        String uri = "/api/car";
        String json = "{\"id\": 1000, \"brandName\": \"abc\", \"price\": 10.0}";
        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)
                );
    }

    @Test
    void testListCarsByCarAvailabilityStatus() throws Exception {
        String uri = "/api/car/listCarsByCarAvailabilityStatus/OPEN";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(status().isOk());
    }

    @Test
    void testListCarsByCarAvailabilityStatus_whenInputStatusViolateTheDefinedEnums() throws Exception {
        String uri = "/api/car/listCarsByCarAvailabilityStatus/OPENd";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof ConstraintViolationException)
                );
    }

    @Test
    void deleteCar() throws Exception{
        String uri = "/api/car/4";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(status().isOk());
    }
}