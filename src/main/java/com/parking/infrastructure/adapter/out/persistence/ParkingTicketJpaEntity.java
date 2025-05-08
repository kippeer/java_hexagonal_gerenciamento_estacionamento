package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingTicketJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String ticketNumber;
    
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;
    
    @Column(name = "slot_id", nullable = false)
    private Long slotId;
    
    @Column(nullable = false)
    private LocalDateTime entryTime;
    
    private LocalDateTime exitTime;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal fee;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;
}