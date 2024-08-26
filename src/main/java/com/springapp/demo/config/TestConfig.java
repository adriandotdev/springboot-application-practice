package com.springapp.demo.config;

import com.springapp.demo.dto.InputStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public InputStudentDTO getTestStudent() {

        return new InputStudentDTO("Test Name", "Test Last Name", "Test Program Name");
    }

    @Bean
    public InputStudentDTO getTestStudent2() {

        return new InputStudentDTO("Test Name 2", "Test Last Name 2", "Test Program Name 2");
    }
}
