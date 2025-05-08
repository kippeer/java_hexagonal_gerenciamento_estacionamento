package com.parking.application.port.in;

import com.parking.domain.model.ParkingSlot;
import com.parking.domain.model.SlotStatus;
import com.parking.domain.model.VehicleType;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotUseCase {
    /**
     * Creates a new parking slot.
     *
     * @param slot The parking slot to create
     * @return The created parking slot with its ID
     */
    ParkingSlot createSlot(ParkingSlot slot);

    /**
     * Updates an existing parking slot.
     *
     * @param slot The parking slot with updated information
     * @return The updated parking slot
     */
    ParkingSlot updateSlot(ParkingSlot slot);

    /**
     * Changes the status of a parking slot.
     *
     * @param slotId The ID of the parking slot
     * @param status The new status
     * @return The updated parking slot
     */
    ParkingSlot changeSlotStatus(Long slotId, SlotStatus status);

    /**
     * Deletes a parking slot.
     *
     * @param slotId The ID of the parking slot to delete
     * @return true if deletion is successful, false otherwise
     */
    boolean deleteSlot(Long slotId);

    /**
     * Finds a parking slot by its ID.
     *
     * @param slotId The ID of the parking slot
     * @return An Optional containing the parking slot if found
     */
    Optional<ParkingSlot> findSlotById(Long slotId);

    /**
     * Finds all parking slots.
     *
     * @return List of all parking slots
     */
    List<ParkingSlot> findAllSlots();

    /**
     * Finds parking slots by status.
     *
     * @param status The status to filter by
     * @return List of parking slots with the specified status
     */
    List<ParkingSlot> findSlotsByStatus(SlotStatus status);

    /**
     * Finds parking slots that can accommodate a specific vehicle type.
     *
     * @param vehicleType The vehicle type
     * @return List of suitable parking slots
     */
    List<ParkingSlot> findSlotsByVehicleType(VehicleType vehicleType);
}