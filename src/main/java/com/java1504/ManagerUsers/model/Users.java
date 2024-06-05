package com.java1504.ManagerUsers.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.java1504.ManagerUsers.ultil.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String dob;

    @Column
    public Gender gender;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String address;


    @OneToMany(mappedBy = "users",cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Set<Bank> banks;

}
