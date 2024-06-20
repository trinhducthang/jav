package com.java1504.ManagerUsers.service;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.model.Bank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankServices {
    public Bank addBank(Bank bank, int id);

    public List<BankDTO> getBanks();

    public Bank updateBank(BankDTO bankDTO, int id);
}
