package com.java1504.ManagerUsers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bank_source;

    private String bank_destination;

    private String amount;

    private LocalDateTime date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false,referencedColumnName = "id")
    @JsonBackReference
    private Users users;

}
