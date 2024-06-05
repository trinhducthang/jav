package com.java1504.ManagerUsers.service;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserServices {

    public List<Users> addUsers(List<Users> users);

    public List<Users> getALL();

    public boolean deleteUser(int id);

    public Users editInfo(int id, Users users);

    public List<UserDTO> getuserdto();

    public UserDTO getUserById(int id);

    public UserDTO updateDto(int id, UserDTO userDTO);

    public Set<BankDTO> getBankDto();

    public Users mapToEntity(UserDTO userDTO);

    public BankDTO mapToDto(Bank bank);

    public Bank mapToEntity(BankDTO bankDTO);

    public Users addUser(Users user);

    public UserDTO updateUser(UserDTO userDTO, int id);
}
