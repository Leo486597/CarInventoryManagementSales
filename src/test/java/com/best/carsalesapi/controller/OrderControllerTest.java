package com.best.carsalesapi.controller;

import com.best.carsalesapi.exception.ApiHandledException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createNewOrder() throws Exception {
        String uri = "/api/order/create";
        String json = "{\"buyerId\": 1, \"sellerId\": 3, \"carsId\": [1], \"hammerPrice\": 10.0}";
        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isOk());
    }

    @Test
    void createNewOrder_whenCarIsAlreadySold_shouldThrowApiHandledException() throws Exception {
        String uri = "/api/order/create";
        String json = "{\"buyerId\": 1, \"sellerId\": 3, \"carsId\": [1, 2, 3], \"hammerPrice\": 10.0}";
        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertTrue(result.getResolvedException() instanceof ApiHandledException);
                });
    }
}