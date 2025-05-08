package com.parking.infrastructure.adapter.out.persistence;

import com.parking.application.port.out.VehicleRepository;
import com.parking.domain.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VehiclePersistenceAdapter implements VehicleRepository {
    private final VehicleJpaRepository vehicleJpaRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleJpaEntity entity = vehicleMapper.toEntity(vehicle);
        VehicleJpaEntity savedEntity = vehicleJpaRepository.save(entity);
        return vehicleMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return vehicleJpaRepository.findById(id)
                .map(vehicleMapper::toDomain);
    }

    @Override
    public Optional<Vehicle> findByLicensePlate(String licensePlate) {
        return vehicleJpaRepository.findByLicensePlate(licensePlate)
                .map(vehicleMapper::toDomain);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleJpaRepository.findAll().stream()
                .map(vehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByCustomerId(Long customerId) {
        return vehicleJpaRepository.findByCustomerId(customerId).stream()
                .map(vehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        vehicleJpaRepository.deleteById(id);
    }
}