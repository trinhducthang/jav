package com.java1504.ManagerUsers.service.impl;


import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.mapper.Mapper;
import com.java1504.ManagerUsers.repository.UsersRepository;
import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServicesImpl implements UserServices {


    final Mapper mapper = new Mapper();

    @Autowired
    private UsersRepository usersRepository;


    public Users addUser(UserDTO userDTO){

        if(checkOverlap(userDTO)){
            Users users = mapper.mapToEntity(userDTO);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            return usersRepository.save(users);
        }
        else{
            throw new RuntimeException("user da ton tai");
        }
    }

    @Override
    public boolean checkOverlap(UserDTO userDTO) {
        List<Users> users = usersRepository.findAll();
        for(Users users1 : users){
            if(users1.getPhone().equals(userDTO.getPhone())){
                return false;
            }

        }
        return true;
    }


    public boolean deleteUser(int id){
        Users users = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        usersRepository.delete(users);
        return true;
    }


    @Override
    public List<UserDTO> getUserDto() {
        List<Users> users = usersRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for(Users user : users)
            userDTOs.add(mapper.mapToDto(user));
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(int id) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        return mapper.mapToDto(users);
    }


    public Users updateUser(Integer id, UserDTO userDTO){
            Users users = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
            users.setPhone(userDTO.getPhone());
            users.setName(userDTO.getName());
            users.setEmail(userDTO.getEmail());
            users.setAddress(userDTO.getAddress());
            users.setDob(userDTO.getDob());
            users.setGender(userDTO.getGender());
            return usersRepository.save(users);

    }


}
