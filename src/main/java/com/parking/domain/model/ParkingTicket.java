package com.parking.domain.model;

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
public class ParkingTicket {
    private Long id;
    private String ticketNumber;
    private Long vehicleId;
    private Long slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private BigDecimal fee;
    private TicketStatus status;
}