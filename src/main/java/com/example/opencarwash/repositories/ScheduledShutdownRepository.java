package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.ScheduledShutdown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduledShutdownRepository extends JpaRepository<ScheduledShutdown, UUID> {
}
