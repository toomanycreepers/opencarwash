package com.example.opencarwash.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalTime;
import java.util.UUID;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "business_hours")
public class BusinessHours {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Setter
    private LocalTime openingTime;

    @NonNull
    @Setter
    private LocalTime closingTime;

    @NonNull
    @Column(name = "weekday")
    @Check(name = "weekday_range",constraints = "weekday BETWEEN 1 AND 7")
    private Integer weekday;

    @NonNull
    @Setter
    private Boolean isClosed;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;
}
