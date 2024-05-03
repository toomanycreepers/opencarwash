package com.example.opencarwash.entities;

import com.example.opencarwash.utils.enums.WorkWeekType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

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
    @Setter
    @Column(name = "number", unique = true)
    @Check(name = "box_num_min_one", constraints = "number >= 1")
    private Integer number;

    @NonNull
    @Enumerated(value = EnumType.ORDINAL)
    private WorkWeekType workWeekType;

    @NonNull
    @ManyToOne
    @JoinColumn(name="carwash_id")
    @Setter
    private Carwash carwash;

    @ManyToMany
    @JoinTable(
    name = "boxes_tariffs",
    joinColumns = @JoinColumn(name = "box_id"),
    inverseJoinColumns = @JoinColumn(name = "tariff_id")
    )
    private Set<Tariff> tariffs = new HashSet<>();

    @OneToMany(mappedBy = "box")
    private Set<BusinessHours> opTime = new HashSet<>();

    @OneToMany(mappedBy = "box")
    private Set<ScheduledShutdown> shutdowns = new HashSet<>();

    public boolean addTariff(Tariff tariff){
        if (tariffs.contains(tariff)){
            return false;
        }
        tariffs.add(tariff);
        return true;
    }

    public boolean removeTariff(Tariff tariff){
        if (!tariffs.contains(tariff)){
            return false;
        }
        tariffs.remove(tariff);
        return true;
    }
}