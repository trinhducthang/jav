package com.java1504.ManagerUsers.service;

import com.java1504.ManagerUsers.model.TransactionHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionHistoryService {
    public List<TransactionHistory> getTransactionHistoryByUser(int userId);
    public String generateCsvData();
}
