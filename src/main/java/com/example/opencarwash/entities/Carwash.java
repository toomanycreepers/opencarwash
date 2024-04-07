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
@Table(name = "carwashes")
public class Carwash {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String city;

    @NonNull
    private String street;

    @NonNull
    private String building;

    @NonNull
    private Short timeslotLengthMinutes;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @ManyToMany
    @JoinTable(
        name = "employees",
        joinColumns = @JoinColumn(name = "carwash_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> employees = new HashSet<>();
}
