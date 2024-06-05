package com.java1504.ManagerUsers.service.impl;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.repository.BanksRepository;
import com.java1504.ManagerUsers.repository.UsersRepository;
import com.java1504.ManagerUsers.service.BankServices;
import com.java1504.ManagerUsers.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankServices {
    @Autowired
    private BanksRepository banksRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    UserServices userServices;

    @Override
    public Bank addBank(Bank bank, int id) {
        Users users = usersRepository.findById(id).get();
        bank.setUsers(users);
        return banksRepository.save(bank);
    }

    @Override
    public List<BankDTO> getBanks() {
        List<Bank> banks = banksRepository.findAll();
        List<BankDTO> bankDTOs = new ArrayList<>();
        for (Bank bank : banks) {
            bankDTOs.add(userServices.mapToDto(bank));
        }
        return bankDTOs;
    }


}
