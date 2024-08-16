package com.java1504.ManagerUsers.service;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserServices {

    public List<Users> findAll();

    public boolean deleteUser(int id);

    public List<UserDTO> getUser();

    @PostAuthorize("returnObject.username == authentication.name")
    public UserDTO getUserById(int id);

    public Users updateUser(Integer id, UserDTO userDTO);

    public Users addUser(UserDTO userDTO);

    public boolean checkOverlap(UserDTO userDTO);

    @PostAuthorize("returnObject.isPresent() ? returnObject.get().username == authentication.name : false")
    public Optional<Users> findByUsername(String username);

    public String getNameByNumber(String number);
}
