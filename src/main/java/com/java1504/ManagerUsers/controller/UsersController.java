package com.java1504.ManagerUsers.controller;


import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.dto.response.ResponseData;
import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.service.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {


    @Autowired
    private UserServices userServices;

    @Operation(summary = "summary", description = "Add Object", responses = {
            
    })
    @PostMapping("/add")
    public ResponseData<?> addUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            userServices.addUser(userDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(),
                    "User added successfully",
                    LocalDateTime.now(),
                    userDTO);
        }
        catch (Exception e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
        public ResponseData<?> deleteUser(@PathVariable int id){
            try {
                return new ResponseData<>(HttpStatus.OK.value(),"Delete successfully",LocalDateTime.now(),userServices.deleteUser(id));
            }
            catch (RuntimeException e){
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage() + "Delete failed");
            }
    }


    @GetMapping("/user")
    public List<UserDTO> getUser(){
        return userServices.getUser();
    }

    @GetMapping("/search/{id}")
    public ResponseData<?> getUserById(@PathVariable int id){
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "GET SUCCESS",
                    LocalDateTime.now(),
                    userServices.getUserById(id));

        }
        catch (Exception e){
            return new ResponseData<>(HttpStatus.NOT_FOUND.value(),
                    e.getMessage());
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseData<?> updateUser(@RequestBody UserDTO userDTO,@PathVariable Integer id){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Update success", LocalDateTime.now(),userServices.updateUser( id,userDTO));

        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/pageable")
    public ResponseData<?> getUserPageable(@RequestParam Integer page){
        Page<Users> usersPage = userServices.getUsers(page);
        return new ResponseData<>(HttpStatus.OK.value(),"ok",LocalDateTime.now(),usersPage);
    }

    @GetMapping("/api/user/{username}")
    public Optional<Users> getUserByUsername(@PathVariable String username) {
        return userServices.findByUsername(username);
    }

    @GetMapping("getBanks/{id}")
    public ResponseData<?> getBanks(@PathVariable int id) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),"get success",LocalDateTime.now(),userServices.getBankByUser(id));
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/getUser/{number}")
    public String getUserByNumber(@PathVariable String number){
        return userServices.getNameByNumber(number);
    }

}
