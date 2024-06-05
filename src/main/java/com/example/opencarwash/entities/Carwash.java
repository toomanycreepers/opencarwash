package com.example.opencarwash.entities;

import com.example.opencarwash.utils.customExceptions.AbsentFromCollectionException;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
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
    @Setter
    private String city;

    @NonNull
    @Setter
    private String street;

    @NonNull
    @Setter
    private String building;

    @Setter
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

    public void addEmployee(User employee) throws AlreadyPresentException{
        if (employees.contains(employee)){
            throw new AlreadyPresentException("User already exists in this context.");
        }
        employees.add(employee);
    }

    public void removeEmployee(User employee) throws AbsentFromCollectionException {
        if (!employees.contains(employee)){
            throw new AbsentFromCollectionException("No such user in this context.");
        }
        employees.remove(employee);
    }
}
