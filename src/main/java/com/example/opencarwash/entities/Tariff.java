package com.example.opencarwash.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String commentForEmployees;

    @NonNull
    private Short bufferTime;
    @OneToMany(mappedBy = "tariff")
    private Set<TariffService> services = new HashSet<>();
}
