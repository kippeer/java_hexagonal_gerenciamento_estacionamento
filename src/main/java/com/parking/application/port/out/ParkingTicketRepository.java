package com.parking.application.port.out;

import com.parking.domain.model.ParkingTicket;
import com.parking.domain.model.TicketStatus;

import java.util.List;
import java.util.Optional;

public interface ParkingTicketRepository {
    ParkingTicket save(ParkingTicket ticket);
    Optional<ParkingTicket> findById(Long id);
    List<ParkingTicket> findAll();
    List<ParkingTicket> findByStatus(TicketStatus status);
    List<ParkingTicket> findByVehicleId(Long vehicleId);
    void delete(Long id);
}