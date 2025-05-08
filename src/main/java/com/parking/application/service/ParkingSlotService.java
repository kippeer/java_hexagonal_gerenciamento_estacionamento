package com.parking.application.service;

import com.parking.application.port.in.ParkingSlotUseCase;
import com.parking.application.port.out.ParkingSlotRepository;
import com.parking.domain.model.ParkingSlot;
import com.parking.domain.model.SlotStatus;
import com.parking.domain.model.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingSlotService implements ParkingSlotUseCase {
    private final ParkingSlotRepository parkingSlotRepository;

    @Override
    @Transactional
    public ParkingSlot createSlot(ParkingSlot slot) {
        // Set default status if not provided
        if (slot.getStatus() == null) {
            slot.setStatus(SlotStatus.AVAILABLE);
        }
        
        return parkingSlotRepository.save(slot);
    }

    @Override
    @Transactional
    public ParkingSlot updateSlot(ParkingSlot slot) {
        // Verify slot exists
        parkingSlotRepository.findById(slot.getId())
                .orElseThrow(() -> new IllegalArgumentException("Parking slot not found with ID: " + slot.getId()));
        
        return parkingSlotRepository.save(slot);
    }

    @Override
    @Transactional
    public ParkingSlot changeSlotStatus(Long slotId, SlotStatus status) {
        ParkingSlot slot = parkingSlotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking slot not found with ID: " + slotId));
        
        slot.setStatus(status);
        return parkingSlotRepository.save(slot);
    }

    @Override
    @Transactional
    public boolean deleteSlot(Long slotId) {
        // Verify slot exists
        parkingSlotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking slot not found with ID: " + slotId));
        
        parkingSlotRepository.delete(slotId);
        return true;
    }

    @Override
    public Optional<ParkingSlot> findSlotById(Long slotId) {
        return parkingSlotRepository.findById(slotId);
    }

    @Override
    public List<ParkingSlot> findAllSlots() {
        return parkingSlotRepository.findAll();
    }

    @Override
    public List<ParkingSlot> findSlotsByStatus(SlotStatus status) {
        return parkingSlotRepository.findByStatus(status);
    }

    @Override
    public List<ParkingSlot> findSlotsByVehicleType(VehicleType vehicleType) {
        return parkingSlotRepository.findByAllowedVehicleTypesContaining(vehicleType);
    }
}