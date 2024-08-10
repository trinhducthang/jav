package com.java1504.ManagerUsers.repository;

import com.java1504.ManagerUsers.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
    List<TransactionHistory> findByUsers_Id(int id);
}
