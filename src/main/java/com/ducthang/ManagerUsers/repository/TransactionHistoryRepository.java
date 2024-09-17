package com.ducthang.ManagerUsers.repository;

import com.ducthang.ManagerUsers.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
    List<TransactionHistory> findByUsers_Id(int id);
}
