package com.parking.infrastructure.adapter.in.web.dto;

import com.parking.domain.model.SlotStatus;
import com.parking.domain.model.SlotType;
import com.parking.domain.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlotDto {
    private Long id;
    
    @NotBlank(message = "Slot number is required")
    private String slotNumber;
    
    @NotNull(message = "Slot type is required")
    private SlotType type;
    
    private SlotStatus status;
    
    @NotNull(message = "Allowed vehicle types are required")
    private Set<VehicleType> allowedVehicleTypes;
    
    @NotBlank(message = "Floor is required")
    private String floor;
    
    @NotBlank(message = "Section is required")
    private String section;
}