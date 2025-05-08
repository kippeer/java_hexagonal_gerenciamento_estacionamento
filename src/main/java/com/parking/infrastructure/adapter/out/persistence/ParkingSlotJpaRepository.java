package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.SlotStatus;
import com.parking.domain.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotJpaRepository extends JpaRepository<ParkingSlotJpaEntity, Long> {
    List<ParkingSlotJpaEntity> findByStatus(SlotStatus status);
    
    @Query("SELECT s FROM ParkingSlotJpaEntity s JOIN s.allowedVehicleTypes t WHERE t = :vehicleType")
    List<ParkingSlotJpaEntity> findByAllowedVehicleTypesContaining(@Param("vehicleType") VehicleType vehicleType);
}