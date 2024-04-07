package com.example.opencarwash.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Table(name = "carwash_services")
public class CarwashService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private Integer price;

    @NonNull
    private String description;

    @NonNull
    private Short duration;
}
