package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.BusinessHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusinessHoursRepository extends JpaRepository<BusinessHours, UUID> {
    @Query("select bh from BusinessHours bh where bh.weekday = :weekDay and bh.box = :boxId")
    Optional<BusinessHours> findBoxWorkDay(@Param("boxId") UUID boxId,@Param("weekDay") Integer weekDay);
}
