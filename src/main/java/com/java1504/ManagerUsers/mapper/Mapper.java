package com.java1504.ManagerUsers.mapper;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.model.Users;
import org.springframework.stereotype.Component;


public class Mapper {


    public Users mapToEntity(UserDTO userDTO){
        Users users = new Users();
        users.setId(userDTO.getId());
        users.setName(userDTO.getName());
        users.setDob(userDTO.getDob());
        users.setGender(userDTO.getGender());
        users.setPhone(userDTO.getPhone());
        users.setEmail(userDTO.getEmail());
        users.setAddress(userDTO.getAddress());
        return users;
    }

    public BankDTO mapToDto(Bank bank){
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(bank.getId());
        bankDTO.setNameBank(bank.getName());
        bankDTO.setNumberBank(bank.getNumberbank());
        bankDTO.setUsersId(bank.getUsers().getId());
        return bankDTO;
    }

    public UserDTO mapToDto(Users users){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setName(users.getName());
        userDTO.setDob(users.getDob());
        userDTO.setGender(users.getGender());
        userDTO.setPhone(users.getPhone());
        userDTO.setEmail(users.getEmail());
        userDTO.setAddress(users.getAddress());
        return userDTO;
    }

    public Bank mapToEntity (BankDTO bankDTO){
        Bank bank = new Bank();
        bank.setId(bankDTO.getId());
        bank.setName(bankDTO.getNameBank());
        bank.setNumberbank(bankDTO.getNumberBank());
        return bank;
    }
}
