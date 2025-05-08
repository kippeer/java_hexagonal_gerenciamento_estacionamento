package com.parking.application.service;

import com.parking.application.port.in.ParkingUseCase;
import com.parking.application.port.out.ParkingSlotRepository;
import com.parking.application.port.out.ParkingTicketRepository;
import com.parking.application.port.out.VehicleRepository;
import com.parking.domain.model.*;
import com.parking.domain.service.FeeCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkingService implements ParkingUseCase {
    private final ParkingTicketRepository parkingTicketRepository;
    private final ParkingSlotRepository parkingSlotRepository;
    private final VehicleRepository vehicleRepository;
    private final FeeCalculationService feeCalculationService;

    @Override
    @Transactional
    public ParkingTicket registerEntry(Vehicle vehicle) {
        // Verify vehicle exists
        Vehicle existingVehicle = vehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicle.getId()));

        // Find suitable parking slot
        ParkingSlot availableSlot = parkingSlotRepository.findByStatus(SlotStatus.AVAILABLE)
                .stream()
                .filter(slot -> slot.getAllowedVehicleTypes().contains(existingVehicle.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No available parking slots for vehicle type: " + existingVehicle.getType()));

        // Update slot status
        availableSlot.setStatus(SlotStatus.OCCUPIED);
        parkingSlotRepository.save(availableSlot);

        // Create and save parking ticket
        ParkingTicket ticket = ParkingTicket.builder()
                .ticketNumber(generateTicketNumber())
                .vehicleId(existingVehicle.getId())
                .slotId(availableSlot.getId())
                .entryTime(LocalDateTime.now())
                .status(TicketStatus.ACTIVE)
                .build();

        return parkingTicketRepository.save(ticket);
    }

    @Override
    @Transactional
    public ParkingTicket registerExit(Long ticketId) {
        // Find active ticket
        ParkingTicket ticket = parkingTicketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found with ID: " + ticketId));

        if (ticket.getStatus() != TicketStatus.ACTIVE) {
            throw new IllegalStateException("Ticket is not active: " + ticket.getTicketNumber());
        }

        // Get vehicle and slot
        Vehicle vehicle = vehicleRepository.findById(ticket.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + ticket.getVehicleId()));
        
        ParkingSlot slot = parkingSlotRepository.findById(ticket.getSlotId())
                .orElseThrow(() -> new IllegalArgumentException("Parking slot not found with ID: " + ticket.getSlotId()));

        // Update ticket with exit time and fee
        LocalDateTime exitTime = LocalDateTime.now();
        ticket.setExitTime(exitTime);
        ticket.setFee(feeCalculationService.calculateFee(vehicle, ticket.getEntryTime(), exitTime));
        ticket.setStatus(TicketStatus.COMPLETED);

        // Update slot status
        slot.setStatus(SlotStatus.AVAILABLE);
        parkingSlotRepository.save(slot);

        // Save and return updated ticket
        return parkingTicketRepository.save(ticket);
    }

    @Override
    public List<ParkingSlot> findAvailableSlots() {
        return parkingSlotRepository.findByStatus(SlotStatus.AVAILABLE);
    }

    @Override
    public Optional<ParkingSlot> findSlotById(Long slotId) {
        return parkingSlotRepository.findById(slotId);
    }

    @Override
    public Optional<ParkingTicket> findTicketById(Long ticketId) {
        return parkingTicketRepository.findById(ticketId);
    }

    @Override
    public List<ParkingTicket> findActiveTickets() {
        return parkingTicketRepository.findByStatus(TicketStatus.ACTIVE);
    }

    @Override
    public List<ParkingTicket> findTicketsByVehicle(Long vehicleId) {
        return parkingTicketRepository.findByVehicleId(vehicleId);
    }

    private String generateTicketNumber() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}