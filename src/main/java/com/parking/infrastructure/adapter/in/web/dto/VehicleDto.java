package com.parking.infrastructure.adapter.in.web.dto;

import com.parking.domain.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    
    @NotBlank(message = "License plate is required")
    private String licensePlate;
    
    @NotNull(message = "Vehicle type is required")
    private VehicleType type;
    
    @NotBlank(message = "Brand is required")
    private String brand;
    
    @NotBlank(message = "Model is required")
    private String model;
    
    private String color;
    
    private Long customerId;
}