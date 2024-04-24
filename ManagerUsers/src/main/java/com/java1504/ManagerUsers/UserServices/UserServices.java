package com.java1504.ManagerUsers.UserServices;

import com.java1504.ManagerUsers.InfoBase.Bank;
import com.java1504.ManagerUsers.InfoBase.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserServices {

    public Users addUsers(Users users);
    public List<Users> getALL();

    public boolean deleteUser(int id);

    public Users editInfo(int id, Users users);
}
