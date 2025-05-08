package com.parking.infrastructure.adapter.in.web.dto;

import com.parking.domain.model.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingTicketDto {
    private Long id;
    private String ticketNumber;
    private Long vehicleId;
    private Long slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private BigDecimal fee;
    private TicketStatus status;
}