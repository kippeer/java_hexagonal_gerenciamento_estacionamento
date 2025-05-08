package com.parking.application.port.out;

import com.parking.domain.model.ParkingSlot;
import com.parking.domain.model.SlotStatus;
import com.parking.domain.model.VehicleType;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotRepository {
    ParkingSlot save(ParkingSlot slot);
    Optional<ParkingSlot> findById(Long id);
    List<ParkingSlot> findAll();
    List<ParkingSlot> findByStatus(SlotStatus status);
    List<ParkingSlot> findByAllowedVehicleTypesContaining(VehicleType vehicleType);
    void delete(Long id);
}