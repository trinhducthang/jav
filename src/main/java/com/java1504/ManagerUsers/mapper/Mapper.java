package com.java1504.ManagerUsers.mapper;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.model.Users;


public class Mapper {


    public Users mapToEntity(UserDTO userDTO){
        Users users = new Users();
        users.setUsername(userDTO.getUsername());
        users.setPassword(userDTO.getPassword());
        users.setName(userDTO.getName());
        users.setDob(userDTO.getDob());
        users.setGender(userDTO.getGender());
        users.setPhone(userDTO.getPhone());
        users.setEmail(userDTO.getEmail());
        users.setAddress(userDTO.getAddress());
        users.setRole(userDTO.getRole());
        return users;
    }

    public BankDTO mapToDto(Bank bank){
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankName(bank.getName());
        bankDTO.setBankNumber(bank.getBankNumber());
        bankDTO.setUsersId(bank.getUsers().getId());
        bankDTO.setBalance(bank.getBalance());
        return bankDTO;
    }

    public UserDTO mapToDto(Users users){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(users.getName());
        userDTO.setDob(users.getDob());
        userDTO.setGender(users.getGender());
        userDTO.setPhone(users.getPhone());
        userDTO.setEmail(users.getEmail());
        userDTO.setAddress(users.getAddress());
        userDTO.setUsername(users.getUsername());
        userDTO.setPassword(users.getPassword());
        userDTO.setRole(users.getRole());
        userDTO.setCreate_at(users.getCreatedAt());
        return userDTO;
    }

    public Bank mapToEntity (BankDTO bankDTO){
        Bank bank = new Bank();
        bank.setName(bankDTO.getBankName());
        bank.setBankNumber(bankDTO.getBankNumber());
        return bank;
    }
}
