package com.java1504.ManagerUsers.repository;

import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanksRepository extends JpaRepository<Bank, Integer> {
    public Bank findByBankNumber(String bankNumber);
    public List<Bank> findByUsers_id(int id);
}
