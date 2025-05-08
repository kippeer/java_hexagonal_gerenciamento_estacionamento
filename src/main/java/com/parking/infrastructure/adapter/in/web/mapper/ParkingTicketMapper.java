package com.parking.infrastructure.adapter.in.web.mapper;

import com.parking.domain.model.ParkingTicket;
import com.parking.infrastructure.adapter.in.web.dto.ParkingTicketDto;
import org.springframework.stereotype.Component;

@Component
public class ParkingTicketMapper {
    
    public ParkingTicketDto toDto(ParkingTicket domain) {
        return ParkingTicketDto.builder()
                .id(domain.getId())
                .ticketNumber(domain.getTicketNumber())
                .vehicleId(domain.getVehicleId())
                .slotId(domain.getSlotId())
                .entryTime(domain.getEntryTime())
                .exitTime(domain.getExitTime())
                .fee(domain.getFee())
                .status(domain.getStatus())
                .build();
    }
    
    public ParkingTicket toDomain(ParkingTicketDto dto) {
        return ParkingTicket.builder()
                .id(dto.getId())
                .ticketNumber(dto.getTicketNumber())
                .vehicleId(dto.getVehicleId())
                .slotId(dto.getSlotId())
                .entryTime(dto.getEntryTime())
                .exitTime(dto.getExitTime())
                .fee(dto.getFee())
                .status(dto.getStatus())
                .build();
    }
}