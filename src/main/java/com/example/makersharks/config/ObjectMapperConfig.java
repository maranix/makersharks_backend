package com.example.makersharks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@Configuration
public class ObjectMapperConfig {
    /**
     * Configures ObjectMapper to
     * 
     * - Omit NULL fields.
     * - Enforce naming strategy to SNAKE_CASE .
     * 
     * @return ObjectMapper
     **/
    @Bean
    ObjectMapper mapper() {
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}
