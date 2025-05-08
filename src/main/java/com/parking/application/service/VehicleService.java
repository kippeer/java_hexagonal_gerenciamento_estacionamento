package com.parking.application.service;

import com.parking.application.port.in.VehicleUseCase;
import com.parking.application.port.out.VehicleRepository;
import com.parking.domain.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService implements VehicleUseCase {
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public Vehicle registerVehicle(Vehicle vehicle) {
        // Check if vehicle with same license plate already exists
        vehicleRepository.findByLicensePlate(vehicle.getLicensePlate())
                .ifPresent(v -> {
                    throw new IllegalArgumentException("Vehicle with license plate already exists: " + vehicle.getLicensePlate());
                });

        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(Vehicle vehicle) {
        // Verify vehicle exists
        vehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicle.getId()));

        // Check if updating to a license plate that already exists on another vehicle
        vehicleRepository.findByLicensePlate(vehicle.getLicensePlate())
                .ifPresent(v -> {
                    if (!v.getId().equals(vehicle.getId())) {
                        throw new IllegalArgumentException("Another vehicle with this license plate already exists: " + vehicle.getLicensePlate());
                    }
                });

        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public boolean deleteVehicle(Long vehicleId) {
        // Verify vehicle exists
        vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicleId));

        vehicleRepository.delete(vehicleId);
        return true;
    }

    @Override
    public Optional<Vehicle> findVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    public Optional<Vehicle> findVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate);
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findVehiclesByCustomer(Long customerId) {
        return vehicleRepository.findByCustomerId(customerId);
    }
}