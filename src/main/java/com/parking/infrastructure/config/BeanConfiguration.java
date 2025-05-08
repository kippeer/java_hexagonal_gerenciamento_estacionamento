package com.parking.infrastructure.config;

import com.parking.domain.service.FeeCalculationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public FeeCalculationService feeCalculationService() {
        return new FeeCalculationService();
    }
}