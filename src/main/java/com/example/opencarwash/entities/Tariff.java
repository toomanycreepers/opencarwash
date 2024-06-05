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

    @Setter
    private String commentForEmployees;

    @Setter
    private Short bufferTime;

    @Setter
    @OneToMany(mappedBy = "tariff")
    private Set<TariffOptionals> services = new HashSet<>();
}
