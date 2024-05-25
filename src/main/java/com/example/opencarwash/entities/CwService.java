package com.example.opencarwash.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Table(name = "carwash_services")
public class CwService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Setter
    private String name;

    @NonNull
    @Setter
    private Integer price;

    @NonNull
    @Setter
    private String description;

    @NonNull
    @Setter
    private Short duration;

    @NonNull
    @ManyToOne
    private Carwash carwash;
}
