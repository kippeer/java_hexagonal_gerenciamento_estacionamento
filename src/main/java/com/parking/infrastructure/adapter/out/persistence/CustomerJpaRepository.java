package com.parking.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long> {
    Optional<CustomerJpaEntity> findByEmail(String email);
}