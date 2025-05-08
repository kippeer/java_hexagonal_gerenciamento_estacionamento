package com.parking.infrastructure.adapter.out.persistence;

import com.parking.application.port.out.ParkingTicketRepository;
import com.parking.domain.model.ParkingTicket;
import com.parking.domain.model.TicketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ParkingTicketPersistenceAdapter implements ParkingTicketRepository {
    private final ParkingTicketJpaRepository parkingTicketJpaRepository;
    private final ParkingTicketMapperOut parkingTicketMapper;

    @Override
    public ParkingTicket save(ParkingTicket ticket) {
        ParkingTicketJpaEntity entity = parkingTicketMapper.toEntity(ticket);
        ParkingTicketJpaEntity savedEntity = parkingTicketJpaRepository.save(entity);
        return parkingTicketMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ParkingTicket> findById(Long id) {
        return parkingTicketJpaRepository.findById(id)
                .map(parkingTicketMapper::toDomain);
    }

    @Override
    public List<ParkingTicket> findAll() {
        return parkingTicketJpaRepository.findAll().stream()
                .map(parkingTicketMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingTicket> findByStatus(TicketStatus status) {
        return parkingTicketJpaRepository.findByStatus(status).stream()
                .map(parkingTicketMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingTicket> findByVehicleId(Long vehicleId) {
        return parkingTicketJpaRepository.findByVehicleId(vehicleId).stream()
                .map(parkingTicketMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        parkingTicketJpaRepository.deleteById(id);
    }
}