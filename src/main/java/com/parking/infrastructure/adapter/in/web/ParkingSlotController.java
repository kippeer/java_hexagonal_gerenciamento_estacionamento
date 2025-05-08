package com.parking.infrastructure.adapter.in.web;

import com.parking.application.port.in.ParkingSlotUseCase;
import com.parking.domain.model.SlotStatus;
import com.parking.domain.model.VehicleType;
import com.parking.infrastructure.adapter.in.web.dto.ParkingSlotDto;
import com.parking.infrastructure.adapter.in.web.dto.SlotStatusUpdateDto;
import com.parking.infrastructure.adapter.in.web.mapper.ParkingSlotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parking-slots")
@RequiredArgsConstructor
public class ParkingSlotController {
    private final ParkingSlotUseCase parkingSlotUseCase;
    private final ParkingSlotMapper parkingSlotMapper;

    @PostMapping
    public ResponseEntity<ParkingSlotDto> createSlot(@Valid @RequestBody ParkingSlotDto slotDto) {
        return new ResponseEntity<>(
                parkingSlotMapper.toDto(parkingSlotUseCase.createSlot(parkingSlotMapper.toDomain(slotDto))),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSlotDto> updateSlot(@PathVariable Long id, 
                                                    @Valid @RequestBody ParkingSlotDto slotDto) {
        if (!id.equals(slotDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(
                parkingSlotMapper.toDto(parkingSlotUseCase.updateSlot(parkingSlotMapper.toDomain(slotDto)))
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ParkingSlotDto> updateSlotStatus(@PathVariable Long id, 
                                                          @Valid @RequestBody SlotStatusUpdateDto statusDto) {
        return ResponseEntity.ok(
                parkingSlotMapper.toDto(parkingSlotUseCase.changeSlotStatus(id, statusDto.getStatus()))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        if (parkingSlotUseCase.deleteSlot(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSlotDto> getSlotById(@PathVariable Long id) {
        return parkingSlotUseCase.findSlotById(id)
                .map(slot -> ResponseEntity.ok(parkingSlotMapper.toDto(slot)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ParkingSlotDto>> getAllSlots() {
        List<ParkingSlotDto> slots = parkingSlotUseCase.findAllSlots().stream()
                .map(parkingSlotMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(slots);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ParkingSlotDto>> getSlotsByStatus(@PathVariable SlotStatus status) {
        List<ParkingSlotDto> slots = parkingSlotUseCase.findSlotsByStatus(status).stream()
                .map(parkingSlotMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(slots);
    }

    @GetMapping("/vehicle-type/{vehicleType}")
    public ResponseEntity<List<ParkingSlotDto>> getSlotsByVehicleType(@PathVariable VehicleType vehicleType) {
        List<ParkingSlotDto> slots = parkingSlotUseCase.findSlotsByVehicleType(vehicleType).stream()
                .map(parkingSlotMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(slots);
    }
}