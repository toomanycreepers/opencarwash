package com.example.opencarwash.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "business_hours")
public class BusinessHours {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private LocalTime openingTime;

    @NonNull
    private LocalTime closingTime;

    @NonNull
    private Byte weekday;

    @NonNull
    private Boolean isClosed;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;
}
