package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.TariffOptionals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TariffOptionalsRepository extends JpaRepository<TariffOptionals, UUID> {
}
