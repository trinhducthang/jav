package com.java1504.ManagerUsers.UserServices;

import com.java1504.ManagerUsers.InfoBase.Bank;
import com.java1504.ManagerUsers.Repository.UsersRepository;
import com.java1504.ManagerUsers.InfoBase.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class IpmUserServices implements UserServices{
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EntityManager entityManager;

    public Users addUsers(Users users){
        return usersRepository.save(users);
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
}
