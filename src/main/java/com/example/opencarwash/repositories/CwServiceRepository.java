package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.CwService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CwServiceRepository extends JpaRepository<CwService, UUID> {
    List<CwService> findAllByCarwashId(UUID carwashId);
}
