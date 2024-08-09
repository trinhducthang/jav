package com.java1504.ManagerUsers.repository;

import com.java1504.ManagerUsers.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByUsers_Id(int id);
}
