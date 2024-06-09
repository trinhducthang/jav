package com.java1504.ManagerUsers.service.impl;

import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.mapper.Mapper;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.repository.UsersRepository;
import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {


    private Mapper mapper = new Mapper();

    @Autowired
    private UsersRepository usersRepository;


    public Users addUser(UserDTO userDTO){

        if(checkOverlap(userDTO)){
            Users users = mapper.mapToEntity(userDTO);
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
            if(usersRepository.existsByNameAndAddressAndDob(users1.getName(),users1.getAddress(),users1.getDob())){
                return false;
            }
        }
        return true;
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
        if (users1 != null){
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
            userDTOs.add(mapper.mapToDto(user));
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(int id) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user khong ton tai"));
        return mapper.mapToDto(users);
    }


    public Users updateUser(int id, UserDTO userDTO){
        Users users = usersRepository.getById(id);
        userDTO.setId(id);
        if(checkOverlap(userDTO)){
            users = mapper.mapToEntity(userDTO);
            return usersRepository.save(users);
        }
        else{
            throw new RuntimeException("user da ton tai");
        }
    }


}
