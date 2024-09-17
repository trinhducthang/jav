package com.java1504.ManagerUsers.service.impl;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.mapper.BankMapper;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.model.TransactionHistory;
import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.repository.BanksRepository;
import com.java1504.ManagerUsers.repository.TransactionHistoryRepository;
import com.java1504.ManagerUsers.repository.UsersRepository;
import com.java1504.ManagerUsers.service.BankServices;
import com.java1504.ManagerUsers.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BankServiceImpl implements BankServices {

    private final BankMapper bankMapper;
    private final BanksRepository banksRepository;
    private final UsersRepository usersRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    final UserServices userServices;

    @Override
    public Bank addBank(Bank bank, int id) {
        Users users = usersRepository.findById(id).get();
        if(checkOverlap(bank)){
            bank.setUsers(users);
            bank.setBalance(0);
            return banksRepository.save(bank);
        }
        throw new RuntimeException("Bank exist with id " + id);
    }

    @Override
    public List<BankDTO> getBanks() {
        List<Bank> banks = banksRepository.findAll();
        List<BankDTO> bankDTOs = new ArrayList<>();
        for (Bank bank : banks) {
            bankDTOs.add(bankMapper.INSTANCE.bankToBankDTO(bank));
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


    @Override
    public Bank bankTransaction(String source, String destination, long amount) {
        Bank bank1 = banksRepository.findByBankNumber(source);
        Bank bank2 = banksRepository.findByBankNumber(destination);
        if(bank1 == null) throw new RuntimeException("Bank source not found");
        if(bank2 == null) throw new RuntimeException("Bank destination not found");
        if(bank1.getBalance() < amount) throw new RuntimeException("Insufficient funds");


        //check authentication if false => break
        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        String username = bank1.getUsers().getUsername();
        if(!authenticationName.equals(username)) throw new RuntimeException("Invalid authentication");

        else{
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setBank_source(bank1.getBankNumber());
            transactionHistory.setBank_destination(bank2.getBankNumber());
            transactionHistory.setAmount(String.valueOf(amount));
            transactionHistory.setDate(LocalDateTime.now());
            transactionHistory.setDescription(bank1.getUsers().getName() + " Transfer");
            transactionHistory.setUsers(bank1.getUsers());
            transactionHistoryRepository.save(transactionHistory);//
            bank1.setBalance(bank1.getBalance() - amount);
            bank2.setBalance(bank2.getBalance() + amount);
            banksRepository.save(bank1);
            banksRepository.save(bank2);
            return banksRepository.save(bank1);
        }

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

    @Override
    public List<Bank> getBankByUser(Integer id) {
        return banksRepository.findByUsers_id(id);
    }

}
