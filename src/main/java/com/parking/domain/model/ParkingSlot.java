package com.parking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {
    private Long id;
    private String slotNumber;
    private SlotType type;
    private SlotStatus status;
    private Set<VehicleType> allowedVehicleTypes;
    private String floor;
    private String section;
}