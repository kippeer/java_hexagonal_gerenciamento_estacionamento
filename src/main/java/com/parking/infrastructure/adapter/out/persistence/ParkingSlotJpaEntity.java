package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.SlotStatus;
import com.parking.domain.model.SlotType;
import com.parking.domain.model.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "parking_slots")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlotJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String slotNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlotType type;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlotStatus status;
    
    @ElementCollection
    @CollectionTable(
        name = "slot_allowed_vehicle_types",
        joinColumns = @JoinColumn(name = "slot_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private Set<VehicleType> allowedVehicleTypes;
    
    @Column(nullable = false)
    private String floor;
    
    @Column(nullable = false)
    private String section;
}