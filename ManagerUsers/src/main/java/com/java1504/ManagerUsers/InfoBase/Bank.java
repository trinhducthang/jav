package com.java1504.ManagerUsers.InfoBase;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Table
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false,referencedColumnName = "id")
    @JsonBackReference
    private Users users;

    @Column
    private String numberbank;

    @Column
    private String Name;



    public Bank() {
    }

    public Bank(int id, String numberbank, String name) {
        Id = id;
        this.numberbank = numberbank;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNumberbank() {
        return numberbank;
    }

    public void setNumberbank(String numberbank) {
        this.numberbank = numberbank;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
