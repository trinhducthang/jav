package com.java1504.ManagerUsers.service.impl;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.mapper.Mapper;
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

    private Mapper mapper = new Mapper();

    @Autowired
    private BanksRepository banksRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    UserServices userServices;

    @Override
    public Bank addBank(Bank bank, int id) {
        Users users = usersRepository.findById(id).get();
        if(checkOverlap(bank)){
            bank.setUsers(users);
            return banksRepository.save(bank);
        }
        throw new RuntimeException("Bank exist with id " + id);
    }

    @Override
    public List<BankDTO> getBanks() {
        List<Bank> banks = banksRepository.findAll();
        List<BankDTO> bankDTOs = new ArrayList<>();
        for (Bank bank : banks) {
            bankDTOs.add(mapper.mapToDto(bank));
        }
        return bankDTOs;
    }

    @Override
    public Bank updateBank(BankDTO bankDTO, int id) {
        Bank bank = banksRepository.findById(id).orElseThrow(()->new RuntimeException("Bank not found"));
        bank.setBankNumber(bankDTO.getBankNumber());
        bank.setName(bank.getName());
        return banksRepository.save(bank);
    }

    public boolean checkOverlap(Bank bank) {
        List<Bank> banks = banksRepository.findAll();
        for (Bank bank1 : banks) {
            if(bank.getBankNumber().equals(bank1.getBankNumber()) && bank1.getName().equals(bank.getName())) {
                return false;
            }
        }
        return true;
    }


}
