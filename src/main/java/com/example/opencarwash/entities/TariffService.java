package com.example.opencarwash.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Table(name = "tariffs_services")
public class TariffService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private Boolean isOptional;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "service_id")
    private CarwashService service;
}
