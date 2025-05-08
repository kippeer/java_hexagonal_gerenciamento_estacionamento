package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.ParkingSlot;
import org.springframework.stereotype.Component;

@Component
public class ParkingSlotMapper {
    
    public ParkingSlot toDomain(ParkingSlotJpaEntity entity) {
        return ParkingSlot.builder()
                .id(entity.getId())
                .slotNumber(entity.getSlotNumber())
                .type(entity.getType())
                .status(entity.getStatus())
                .allowedVehicleTypes(entity.getAllowedVehicleTypes())
                .floor(entity.getFloor())
                .section(entity.getSection())
                .build();
    }
    
    public ParkingSlotJpaEntity toEntity(ParkingSlot domain) {
        return ParkingSlotJpaEntity.builder()
                .id(domain.getId())
                .slotNumber(domain.getSlotNumber())
                .type(domain.getType())
                .status(domain.getStatus())
                .allowedVehicleTypes(domain.getAllowedVehicleTypes())
                .floor(domain.getFloor())
                .section(domain.getSection())
                .build();
    }
}