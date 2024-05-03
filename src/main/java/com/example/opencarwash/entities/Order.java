package com.example.opencarwash.entities;

import com.example.opencarwash.utils.enums.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private LocalDateTime startTime;

    private Short rating;

    private String feedback;

    @NonNull
    @Enumerated(value = EnumType.ORDINAL)
    private OrderState state;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany
    private Set<Photo> photos = new HashSet<>();
}
