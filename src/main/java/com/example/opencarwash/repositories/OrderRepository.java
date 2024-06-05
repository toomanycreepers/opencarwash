package com.example.opencarwash.repositories;

import com.example.opencarwash.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByBoxId(UUID boxId);

    @Query("SELECT o FROM Order o WHERE CAST(o.startTime AS LocalDate) = :date AND o.box.id = :boxId")
    List<Order> findByBoxAndDate(@Param("boxId") UUID boxId, @Param("date") LocalDate date);
}
