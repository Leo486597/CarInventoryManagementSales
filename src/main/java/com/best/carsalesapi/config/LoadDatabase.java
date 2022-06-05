package com.best.carsalesapi.config;

import com.best.carsalesapi.service.MockDataGeneratorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(MockDataGeneratorService service) {
        return args -> {
            service.generateCars();
            service.generateUsers();
        };
    }
}
