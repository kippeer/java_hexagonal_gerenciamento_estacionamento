package com.parking.infrastructure.adapter.in.web;

import com.parking.application.port.in.ParkingUseCase;
import com.parking.application.port.in.VehicleUseCase;
import com.parking.infrastructure.adapter.in.web.dto.ParkingSlotDto;
import com.parking.infrastructure.adapter.in.web.dto.ParkingTicketDto;
import com.parking.infrastructure.adapter.in.web.dto.VehicleEntryDto;
import com.parking.infrastructure.adapter.in.web.mapper.ParkingSlotMapper;
import com.parking.infrastructure.adapter.in.web.mapper.ParkingTicketMapper;
import com.parking.infrastructure.adapter.in.web.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {
    private final ParkingUseCase parkingUseCase;
    private final VehicleUseCase vehicleUseCase;
    private final ParkingTicketMapper parkingTicketMapper;
    private final ParkingSlotMapper parkingSlotMapper;
    private final VehicleMapper vehicleMapper;

    @PostMapping("/entry")
    public ResponseEntity<ParkingTicketDto> registerEntry(@Valid @RequestBody VehicleEntryDto entryDto) {
        return vehicleUseCase.findVehicleById(entryDto.getVehicleId())
                .map(vehicle -> new ResponseEntity<>(
                        parkingTicketMapper.toDto(parkingUseCase.registerEntry(vehicle)),
                        HttpStatus.CREATED
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/exit/{ticketId}")
    public ResponseEntity<ParkingTicketDto> registerExit(@PathVariable Long ticketId) {
        return ResponseEntity.ok(
                parkingTicketMapper.toDto(parkingUseCase.registerExit(ticketId))
        );
    }

    @GetMapping("/slots/available")
    public ResponseEntity<List<ParkingSlotDto>> getAvailableSlots() {
        List<ParkingSlotDto> slots = parkingUseCase.findAvailableSlots().stream()
                .map(parkingSlotMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(slots);
    }

    @GetMapping("/slots/{slotId}")
    public ResponseEntity<ParkingSlotDto> getSlotById(@PathVariable Long slotId) {
        return parkingUseCase.findSlotById(slotId)
                .map(slot -> ResponseEntity.ok(parkingSlotMapper.toDto(slot)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<ParkingTicketDto> getTicketById(@PathVariable Long ticketId) {
        return parkingUseCase.findTicketById(ticketId)
                .map(ticket -> ResponseEntity.ok(parkingTicketMapper.toDto(ticket)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tickets/active")
    public ResponseEntity<List<ParkingTicketDto>> getActiveTickets() {
        List<ParkingTicketDto> tickets = parkingUseCase.findActiveTickets().stream()
                .map(parkingTicketMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/tickets/vehicle/{vehicleId}")
    public ResponseEntity<List<ParkingTicketDto>> getTicketsByVehicle(@PathVariable Long vehicleId) {
        List<ParkingTicketDto> tickets = parkingUseCase.findTicketsByVehicle(vehicleId).stream()
                .map(parkingTicketMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(tickets);
    }
}