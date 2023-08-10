package com.example.examservice.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /** Bean to create an object of Mode Mapper to later use it for mapping DTOs into entities and vice versa */
     @Bean
     public ModelMapper modelMapper() {
         ModelMapper modelMapper = new ModelMapper();
         return modelMapper;
     }
}
