package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, UUID> {
}
