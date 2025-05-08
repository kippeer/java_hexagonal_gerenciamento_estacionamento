package com.parking.application.port.in;

import com.parking.domain.model.ParkingSlot;
import com.parking.domain.model.ParkingTicket;
import com.parking.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingUseCase {
    /**
     * Registers a vehicle entry and assigns it to an available parking slot.
     *
     * @param vehicle The vehicle entering the parking
     * @return A parking ticket with entry information
     */
    ParkingTicket registerEntry(Vehicle vehicle);

    /**
     * Registers a vehicle exit, calculates the fee, and updates the ticket.
     *
     * @param ticketId The ID of the parking ticket
     * @return The updated parking ticket with exit information and fee
     */
    ParkingTicket registerExit(Long ticketId);

    /**
     * Finds all available parking slots.
     *
     * @return List of available parking slots
     */
    List<ParkingSlot> findAvailableSlots();

    /**
     * Finds a parking slot by its ID.
     *
     * @param slotId The ID of the parking slot
     * @return An Optional containing the parking slot if found
     */
    Optional<ParkingSlot> findSlotById(Long slotId);

    /**
     * Finds a parking ticket by its ID.
     *
     * @param ticketId The ID of the parking ticket
     * @return An Optional containing the parking ticket if found
     */
    Optional<ParkingTicket> findTicketById(Long ticketId);

    /**
     * Finds all active parking tickets.
     *
     * @return List of active parking tickets
     */
    List<ParkingTicket> findActiveTickets();

    /**
     * Finds all parking tickets for a specific vehicle.
     *
     * @param vehicleId The ID of the vehicle
     * @return List of parking tickets for the vehicle
     */
    List<ParkingTicket> findTicketsByVehicle(Long vehicleId);
}