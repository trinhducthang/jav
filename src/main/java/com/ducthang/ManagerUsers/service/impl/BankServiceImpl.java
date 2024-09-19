package com.ducthang.ManagerUsers.service.impl;

import com.ducthang.ManagerUsers.dto.BankTransferDto;
import com.ducthang.ManagerUsers.mapper.BankMapper;
import com.ducthang.ManagerUsers.model.Bank;
import com.ducthang.ManagerUsers.model.TransactionHistory;
import com.ducthang.ManagerUsers.model.Users;
import com.ducthang.ManagerUsers.repository.BanksRepository;
import com.ducthang.ManagerUsers.repository.TransactionHistoryRepository;
import com.ducthang.ManagerUsers.repository.UsersRepository;
import com.ducthang.ManagerUsers.service.BankServices;
import com.ducthang.ManagerUsers.service.UserServices;
import com.ducthang.ManagerUsers.dto.BankDTO;
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

    @Override
    public boolean TransferOtherBank(BankTransferDto request) {
        Bank bankSource = banksRepository.findByNameAndBankNumber(request.getNameSource(), request.getBankNumberSource());
        if(bankSource == null) throw new RuntimeException("Bank sources not found");
        Bank bankDestination = banksRepository.findByNameAndBankNumber(request.getNameDestination(), request.getBankNumberDestination());
        if(bankDestination == null) throw new RuntimeException("Bank destination not found");
        if(bankSource.getBalance() > bankDestination.getBalance()) throw new RuntimeException("Insufficient funds");

        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        String username = bankSource.getUsers().getUsername();
        if(!authenticationName.equals(username)) throw new RuntimeException("Invalid authentication");
        else{
            bankSource.setBalance(bankSource.getBalance() - request.getAmount());
            bankDestination.setBalance(bankDestination.getBalance() + request.getAmount());
            banksRepository.save(bankSource);
            banksRepository.save(bankDestination);
        }
        return true;
    }

    @Override
    public Bank GetBank(String bankName, String bankNumber) {
        return banksRepository.findByNameAndBankNumber(bankName, bankNumber);
    }


}
