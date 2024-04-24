package com.java1504.ManagerUsers.UsersController;


import com.java1504.ManagerUsers.InfoBase.Bank;
import com.java1504.ManagerUsers.UserServices.UserServices;
import com.java1504.ManagerUsers.InfoBase.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UsersController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/add")
    public Users addUsers(@RequestBody Users users){
        return userServices.addUsers(users);
    }

    @GetMapping
    public List<Users> getAll(){

        return userServices.getALL();
    }

    @DeleteMapping("/{id}")
        public boolean deleteUser( @PathVariable int id){
            return userServices.deleteUser(id);
    }

    @PutMapping("/edit/{id}")
    public Users editInfo(@PathVariable int id,@RequestBody Users users){
        return userServices.editInfo(id,users);
    }

}
