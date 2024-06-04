package com.java1504.ManagerUsers.service.impl;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.repository.UsersRepository;
import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.service.UserServices;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Users> addUsers(List<Users> users){
        return usersRepository.saveAll(users);
    }

    public Users addUser(Users user){
        return usersRepository.save(user);
    }

    @Override
    public Set<Bank> addBank(Set<Bank> bank, int id) {
        Users user = usersRepository.findById(id).get();
        if(user != null){
            user.setBanks(bank);
            return usersRepository.save(user).getBanks();
        }
        return null;
    }

    public List<Users> getALL(){
        return usersRepository.findAll();
    }

    public boolean deleteUser(int id){
        Users users = usersRepository.getById(id);
        if(users != null){
            usersRepository.delete(users);
            return true;
        }
        return false;
    }

    public Users editInfo(int id, Users users){
        Users users1 = usersRepository.getById(id);
        if (    users1 != null){
            users1.setName(users.getName());
            users1.setPhone(users.getPhone());
            users1.setDob(users.getDob());
            users1.setGender(users.getGender());
            users1.setBanks(users.getBanks());
            return usersRepository.save(users1);
        }
        return null;
    }

    @Override
    public List<UserDTO> getuserdto() {
        List<Users> users = usersRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for(Users user : users)
            userDTOs.add(mapToDto(user));
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(int id) {
        return mapToDto(usersRepository.getById(id));
    }

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

    public UserDTO updateDto(int id, UserDTO userDTO){
        Users users = usersRepository.getById(id);
        userDTO.setId(id);
        users = mapToEntity(userDTO);
        usersRepository.save(users);
        return userDTO;
    }

    public Set<BankDTO> getBankDto(){
        Set<BankDTO> bankDTOS = new HashSet<>();
        List<Users> users = usersRepository.findAll();
        for (Users users1 : users){
            for (Bank bank : users1.getBanks()){
                bankDTOS.add(mapToDto(bank));
            }
        }
        return  bankDTOS;
    }
}
