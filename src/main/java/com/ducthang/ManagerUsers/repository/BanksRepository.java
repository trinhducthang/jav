package com.ducthang.ManagerUsers.repository;

import com.ducthang.ManagerUsers.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanksRepository extends JpaRepository<Bank, Integer> {
    public Bank findByBankNumber(String bankNumber);
    public List<Bank> findByUsers_id(int id);
}
