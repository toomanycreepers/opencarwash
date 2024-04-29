package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.Carwash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarwashRepository extends JpaRepository<Carwash, UUID> {
}
