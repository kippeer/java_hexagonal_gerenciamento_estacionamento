package com.parking.infrastructure.adapter.in.web.mapper;

import com.parking.domain.model.Vehicle;
import com.parking.infrastructure.adapter.in.web.dto.VehicleDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    
    public VehicleDto toDto(Vehicle domain) {
        return VehicleDto.builder()
                .id(domain.getId())
                .licensePlate(domain.getLicensePlate())
                .type(domain.getType())
                .brand(domain.getBrand())
                .model(domain.getModel())
                .color(domain.getColor())
                .customerId(domain.getCustomerId())
                .build();
    }
    
    public Vehicle toDomain(VehicleDto dto) {
        return Vehicle.builder()
                .id(dto.getId())
                .licensePlate(dto.getLicensePlate())
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .color(dto.getColor())
                .customerId(dto.getCustomerId())
                .build();
    }
}