package com.java1504.ManagerUsers.repository;

import com.java1504.ManagerUsers.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanksRepository extends JpaRepository<Bank, Integer> {
}
