package com.java1504.ManagerUsers.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.java1504.ManagerUsers.ultil.Gender;
import jakarta.persistence.*;
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

    private String username;

    private String password;


    private String name;


    private String dob;


    public Gender gender;


    private String phone;


    private String email;


    private String address;


    @OneToMany(mappedBy = "users",cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Set<Bank> banks;

}
