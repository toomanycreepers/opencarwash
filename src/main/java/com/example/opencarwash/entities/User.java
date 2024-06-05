package com.example.opencarwash.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Table(name = "users_general")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Setter
    private String phoneNumber;

    @NonNull
    @Setter
    private String firstName;

    @NonNull
    @Setter
    private String lastName;

    @NonNull
    private String password;

    private String salt;

    @Lob
    @Setter
    private byte[] picture;

    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles = new HashSet<>();

    public void setPassword(String password) {
        generateSalt();
        password = BCrypt.hashpw(password, salt);
        this.password = password;
    }

    private void generateSalt(){
        if (this.salt==null)
            this.salt= BCrypt.gensalt();
    }
}
