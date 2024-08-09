package com.java1504.ManagerUsers.repository;

import com.java1504.ManagerUsers.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
}
