package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingTicketJpaRepository extends JpaRepository<ParkingTicketJpaEntity, Long> {
    List<ParkingTicketJpaEntity> findByStatus(TicketStatus status);
    List<ParkingTicketJpaEntity> findByVehicleId(Long vehicleId);
}