package com.parking.infrastructure.adapter.out.persistence;

import com.parking.application.port.out.ParkingSlotRepository;
import com.parking.domain.model.ParkingSlot;
import com.parking.domain.model.SlotStatus;
import com.parking.domain.model.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ParkingSlotPersistenceAdapter implements ParkingSlotRepository {
    private final ParkingSlotJpaRepository parkingSlotJpaRepository;
    private final ParkingSlotMapper parkingSlotMapper;

    @Override
    public ParkingSlot save(ParkingSlot slot) {
        ParkingSlotJpaEntity entity = parkingSlotMapper.toEntity(slot);
        ParkingSlotJpaEntity savedEntity = parkingSlotJpaRepository.save(entity);
        return parkingSlotMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ParkingSlot> findById(Long id) {
        return parkingSlotJpaRepository.findById(id)
                .map(parkingSlotMapper::toDomain);
    }

    @Override
    public List<ParkingSlot> findAll() {
        return parkingSlotJpaRepository.findAll().stream()
                .map(parkingSlotMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSlot> findByStatus(SlotStatus status) {
        return parkingSlotJpaRepository.findByStatus(status).stream()
                .map(parkingSlotMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSlot> findByAllowedVehicleTypesContaining(VehicleType vehicleType) {
        return parkingSlotJpaRepository.findByAllowedVehicleTypesContaining(vehicleType).stream()
                .map(parkingSlotMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        parkingSlotJpaRepository.deleteById(id);
    }
}