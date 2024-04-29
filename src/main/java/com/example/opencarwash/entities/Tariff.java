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
    @Setter
    private String name;

    @NonNull
    @Setter
    private String description;

    @NonNull
    @Setter
    private String commentForEmployees;

    @NonNull
    @Setter
    private Short bufferTime;

    @OneToMany(mappedBy = "tariff")
    private Set<TariffOptionals> services = new HashSet<>();
}
