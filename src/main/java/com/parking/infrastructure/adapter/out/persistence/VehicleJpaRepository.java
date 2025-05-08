package com.parking.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleJpaRepository extends JpaRepository<VehicleJpaEntity, Long> {
    Optional<VehicleJpaEntity> findByLicensePlate(String licensePlate);
    List<VehicleJpaEntity> findByCustomerId(Long customerId);
}