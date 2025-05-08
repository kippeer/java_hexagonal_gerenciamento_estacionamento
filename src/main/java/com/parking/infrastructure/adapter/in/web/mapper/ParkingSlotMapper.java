package com.parking.infrastructure.adapter.in.web.mapper;

import com.parking.domain.model.ParkingSlot;
import com.parking.infrastructure.adapter.in.web.dto.ParkingSlotDto;
import org.springframework.stereotype.Component;

@Component
public class ParkingSlotMapper {
    
    public ParkingSlotDto toDto(ParkingSlot domain) {
        return ParkingSlotDto.builder()
                .id(domain.getId())
                .slotNumber(domain.getSlotNumber())
                .type(domain.getType())
                .status(domain.getStatus())
                .allowedVehicleTypes(domain.getAllowedVehicleTypes())
                .floor(domain.getFloor())
                .section(domain.getSection())
                .build();
    }
    
    public ParkingSlot toDomain(ParkingSlotDto dto) {
        return ParkingSlot.builder()
                .id(dto.getId())
                .slotNumber(dto.getSlotNumber())
                .type(dto.getType())
                .status(dto.getStatus())
                .allowedVehicleTypes(dto.getAllowedVehicleTypes())
                .floor(dto.getFloor())
                .section(dto.getSection())
                .build();
    }
}