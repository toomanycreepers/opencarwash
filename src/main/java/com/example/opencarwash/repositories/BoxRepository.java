package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoxRepository extends JpaRepository<Box, UUID> {
    List<Box> findAllByCarwashId(UUID carwashId);
}
