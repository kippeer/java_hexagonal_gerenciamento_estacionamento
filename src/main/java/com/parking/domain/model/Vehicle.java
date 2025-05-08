package com.parking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private String licensePlate;
    private VehicleType type;
    private String brand;
    private String model;
    private String color;
    private Long customerId;
}