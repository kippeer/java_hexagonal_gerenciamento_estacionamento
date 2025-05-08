package com.parking.domain.service;

import com.parking.domain.model.ParkingTicket;
import com.parking.domain.model.Vehicle;
import com.parking.domain.model.VehicleType;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculationService {

    private static final BigDecimal HOURLY_RATE_CAR = new BigDecimal("5.00");
    private static final BigDecimal HOURLY_RATE_MOTORCYCLE = new BigDecimal("3.00");
    private static final BigDecimal HOURLY_RATE_SUV = new BigDecimal("7.00");
    private static final BigDecimal HOURLY_RATE_TRUCK = new BigDecimal("10.00");
    private static final BigDecimal HOURLY_RATE_ELECTRIC = new BigDecimal("6.00");

    public BigDecimal calculateFee(Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime) {
        Duration parkingDuration = Duration.between(entryTime, exitTime);
        long hours = parkingDuration.toHours();
        // If there's a partial hour, count it as a full hour
        if (parkingDuration.toMinutes() % 60 > 0) {
            hours++;
        }

        BigDecimal hourlyRate = getHourlyRateByVehicleType(vehicle.getType());
        return hourlyRate.multiply(BigDecimal.valueOf(hours));
    }

    public BigDecimal calculateFee(ParkingTicket ticket, Vehicle vehicle) {
        if (ticket.getExitTime() == null) {
            throw new IllegalArgumentException("Exit time not set for ticket: " + ticket.getTicketNumber());
        }
        return calculateFee(vehicle, ticket.getEntryTime(), ticket.getExitTime());
    }

    private BigDecimal getHourlyRateByVehicleType(VehicleType type) {
        return switch (type) {
            case CAR -> HOURLY_RATE_CAR;
            case MOTORCYCLE -> HOURLY_RATE_MOTORCYCLE;
            case SUV -> HOURLY_RATE_SUV;
            case TRUCK -> HOURLY_RATE_TRUCK;
            case ELECTRIC_VEHICLE -> HOURLY_RATE_ELECTRIC;
        };
    }
}