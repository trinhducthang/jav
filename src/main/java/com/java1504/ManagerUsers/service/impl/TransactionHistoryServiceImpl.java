package com.java1504.ManagerUsers.service.impl;

import com.java1504.ManagerUsers.model.TransactionHistory;
import com.java1504.ManagerUsers.repository.TransactionHistoryRepository;
import com.java1504.ManagerUsers.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;


    @Override
    public List<TransactionHistory> getTransactionHistoryByUser(int userId) {
        List<TransactionHistory> transactionHistories = transactionHistoryRepository.findByUsers_Id(userId);
        Comparator<TransactionHistory> c = new Comparator<TransactionHistory>() {
            @Override
            public int compare(TransactionHistory o1, TransactionHistory o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        };
        Collections.sort(transactionHistories, c);
        return transactionHistories;
    }

    public String generateCsvData() {
        List<TransactionHistory> transactionHistories = transactionHistoryRepository.findAll();

        try (StringWriter out = new StringWriter();
             CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT
                     .withHeader("ID", "Bank Source", "Bank Destination", "Amount", "Date", "Description", "User ID"))) {

            for (TransactionHistory transaction : transactionHistories) {
                csvPrinter.printRecord(
                        transaction.getId(),
                        transaction.getBank_source(),
                        transaction.getBank_destination(),
                        transaction.getAmount(),
                        transaction.getDate(),
                        transaction.getDescription(),
                        transaction.getUsers().getId()
                );
            }
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating CSV data", e);
        }
    }
}
