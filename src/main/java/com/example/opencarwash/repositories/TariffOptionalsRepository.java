package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.TariffOptionals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TariffOptionalsRepository extends JpaRepository<TariffOptionals, UUID> {
    boolean existsByTariffIdAndServiceId(UUID tariffId, UUID serviceId);
    Set<TariffOptionals> findAllByTariffId(UUID tariffId);
    Optional<TariffOptionals> findByTariffIdAndServiceId(UUID tariffId, UUID serviceId);
}