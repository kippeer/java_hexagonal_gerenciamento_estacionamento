package com.parking.infrastructure.adapter.in.web;

import com.parking.application.port.in.VehicleUseCase;
import com.parking.infrastructure.adapter.in.web.dto.VehicleDto;
import com.parking.infrastructure.adapter.in.web.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleUseCase vehicleUseCase;
    private final VehicleMapper vehicleMapper;

    @PostMapping
    public ResponseEntity<VehicleDto> registerVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        return new ResponseEntity<>(
                vehicleMapper.toDto(vehicleUseCase.registerVehicle(vehicleMapper.toDomain(vehicleDto))),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Long id, 
                                                   @Valid @RequestBody VehicleDto vehicleDto) {
        if (!id.equals(vehicleDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(
                vehicleMapper.toDto(vehicleUseCase.updateVehicle(vehicleMapper.toDomain(vehicleDto)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        if (vehicleUseCase.deleteVehicle(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long id) {
        return vehicleUseCase.findVehicleById(id)
                .map(vehicle -> ResponseEntity.ok(vehicleMapper.toDto(vehicle)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/license-plate/{licensePlate}")
    public ResponseEntity<VehicleDto> getVehicleByLicensePlate(@PathVariable String licensePlate) {
        return vehicleUseCase.findVehicleByLicensePlate(licensePlate)
                .map(vehicle -> ResponseEntity.ok(vehicleMapper.toDto(vehicle)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleUseCase.findAllVehicles().stream()
                .map(vehicleMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<VehicleDto>> getVehiclesByCustomer(@PathVariable Long customerId) {
        List<VehicleDto> vehicles = vehicleUseCase.findVehiclesByCustomer(customerId).stream()
                .map(vehicleMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(vehicles);
    }
}