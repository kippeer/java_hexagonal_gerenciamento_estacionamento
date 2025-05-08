package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String licensePlate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;
    
    @Column(nullable = false)
    private String brand;
    
    @Column(nullable = false)
    private String model;
    
    private String color;
    
    @Column(name = "customer_id")
    private Long customerId;
}