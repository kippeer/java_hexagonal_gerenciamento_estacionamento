package com.parking.application.port.out;

import com.parking.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    List<Vehicle> findAll();
    List<Vehicle> findByCustomerId(Long customerId);
    void delete(Long id);
}