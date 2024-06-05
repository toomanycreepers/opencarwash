package com.example.opencarwash.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class ScheduledShutdown {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private LocalDateTime start;

    @NonNull
    private LocalDateTime finish;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;
}
