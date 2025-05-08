package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.ParkingTicket;
import org.springframework.stereotype.Component;

@Component
public class ParkingTicketMapper {
    
    public ParkingTicket toDomain(ParkingTicketJpaEntity entity) {
        return ParkingTicket.builder()
                .id(entity.getId())
                .ticketNumber(entity.getTicketNumber())
                .vehicleId(entity.getVehicleId())
                .slotId(entity.getSlotId())
                .entryTime(entity.getEntryTime())
                .exitTime(entity.getExitTime())
                .fee(entity.getFee())
                .status(entity.getStatus())
                .build();
    }
    
    public ParkingTicketJpaEntity toEntity(ParkingTicket domain) {
        return ParkingTicketJpaEntity.builder()
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
}