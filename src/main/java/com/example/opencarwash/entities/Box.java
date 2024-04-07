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
@Table(name = "boxes")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private Short number;

    @NonNull
    @ManyToOne
    @JoinColumn(name="carwash_id")
    private Carwash carwash;

    @ManyToMany
    @JoinTable(
    name = "boxes_tariffs",
    joinColumns = @JoinColumn(name = "box_id"),
    inverseJoinColumns = @JoinColumn(name = "tariff_id")
    )
    private Set<Tariff> tariffs = new HashSet<>();

}
