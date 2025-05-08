package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapperOut {
    
    public Vehicle toDomain(VehicleJpaEntity entity) {
        return Vehicle.builder()
                .id(entity.getId())
                .licensePlate(entity.getLicensePlate())
                .type(entity.getType())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .color(entity.getColor())
                .customerId(entity.getCustomerId())
                .build();
    }
    
    public VehicleJpaEntity toEntity(Vehicle domain) {
        return VehicleJpaEntity.builder()
                .id(domain.getId())
                .licensePlate(domain.getLicensePlate())
                .type(domain.getType())
                .brand(domain.getBrand())
                .model(domain.getModel())
                .color(domain.getColor())
                .customerId(domain.getCustomerId())
                .build();
    }
}