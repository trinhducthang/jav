package com.java1504.ManagerUsers.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.java1504.ManagerUsers.enums.Role;
import com.java1504.ManagerUsers.ultil.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String username;
    String password;
    String name;
    String dob;
    Gender gender;
    String phone;
    String email;
    String address;

    Role role;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "users",cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Set<Bank> banks;

}
