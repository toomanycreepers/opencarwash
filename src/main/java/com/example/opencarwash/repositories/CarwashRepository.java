package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.Carwash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarwashRepository extends JpaRepository<Carwash, UUID> {
    List<Carwash> findAllByFranchiseId(UUID franchiseId);

    @Query("select cw from Carwash cw join cw.employees e where e.id = :employeeId")
    List<Carwash> findByEmployeeId(@Param("employeeId") UUID employeeId);
}
