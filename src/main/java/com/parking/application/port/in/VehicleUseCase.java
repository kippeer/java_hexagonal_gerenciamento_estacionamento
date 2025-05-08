package com.parking.application.port.in;

import com.parking.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleUseCase {
    /**
     * Registers a new vehicle in the system.
     *
     * @param vehicle The vehicle to register
     * @return The registered vehicle with its ID
     */
    Vehicle registerVehicle(Vehicle vehicle);

    /**
     * Updates an existing vehicle's information.
     *
     * @param vehicle The vehicle with updated information
     * @return The updated vehicle
     */
    Vehicle updateVehicle(Vehicle vehicle);

    /**
     * Deletes a vehicle from the system.
     *
     * @param vehicleId The ID of the vehicle to delete
     * @return true if deletion is successful, false otherwise
     */
    boolean deleteVehicle(Long vehicleId);

    /**
     * Finds a vehicle by its ID.
     *
     * @param vehicleId The ID of the vehicle
     * @return An Optional containing the vehicle if found
     */
    Optional<Vehicle> findVehicleById(Long vehicleId);

    /**
     * Finds a vehicle by its license plate.
     *
     * @param licensePlate The license plate of the vehicle
     * @return An Optional containing the vehicle if found
     */
    Optional<Vehicle> findVehicleByLicensePlate(String licensePlate);

    /**
     * Finds all vehicles in the system.
     *
     * @return List of all vehicles
     */
    List<Vehicle> findAllVehicles();

    /**
     * Finds all vehicles for a specific customer.
     *
     * @param customerId The ID of the customer
     * @return List of vehicles for the customer
     */
    List<Vehicle> findVehiclesByCustomer(Long customerId);
}