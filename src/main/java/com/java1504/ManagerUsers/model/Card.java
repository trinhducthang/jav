package com.java1504.ManagerUsers.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String card_number;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    private String cvv;

    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false,referencedColumnName = "id")
    @JsonBackReference
    private Users users;

    public Card() {
        this.card_number = generateCardId();
        this.expiryDate = LocalDateTime.now().plus(5, ChronoUnit.YEARS);
        this.cvv = String.valueOf(100 + (int)(Math.random() * 900));
    }

    public static String generateCardId() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16).replaceAll("(.{4})", "$1-").substring(0, 19);
    }
}
