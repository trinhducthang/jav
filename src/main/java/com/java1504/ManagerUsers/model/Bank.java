package com.java1504.ManagerUsers.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;


    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false,referencedColumnName = "id")
    @JsonBackReference
    private Users users;

    @Column
    private String bankNumber;


    @Column
    private String name;


    public Bank() {
    }

    public Bank(int id, String bankNumber, String name) {
        Id = id;
        this.bankNumber = bankNumber;
        this.name = name;
    }

}
