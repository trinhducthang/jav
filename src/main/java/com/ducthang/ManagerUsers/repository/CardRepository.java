package com.ducthang.ManagerUsers.repository;

import com.ducthang.ManagerUsers.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByUsers_Id(int id);
}
