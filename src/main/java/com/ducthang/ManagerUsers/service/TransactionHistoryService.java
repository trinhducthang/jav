package com.ducthang.ManagerUsers.service;

import com.ducthang.ManagerUsers.model.TransactionHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionHistoryService {
    public List<TransactionHistory> getTransactionHistoryByUser(int userId);
    public String generateCsvData();
}
