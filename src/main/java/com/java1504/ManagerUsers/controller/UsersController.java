package com.java1504.ManagerUsers.controller;


import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.response.ResponseData;
import com.java1504.ManagerUsers.service.UserServices;
import com.java1504.ManagerUsers.model.Users;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class UsersController {


    @Autowired
    private UserServices userServices;

    @Operation(summary = "summary", description = "Add Object", responses = {
            
    })
    @PostMapping("/addDto")
    public ResponseData<?> addUserDto(@Valid @RequestBody UserDTO userDTO) {
        try {
            Users user = userServices.mapToEntity(userDTO);
            userServices.addUser(user);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User added successfully", LocalDateTime.now(), user);
        }
        catch (Exception e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Users> getAll(){
        return userServices.getALL();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
        public boolean deleteUser(@PathVariable int id){
            return userServices.deleteUser(id);
    }


    @GetMapping("/user")
    public List<UserDTO> getUserDto(){
        return userServices.getuserdto();
    }

    @GetMapping("/search/{id}")
    public ResponseData<?> getUserById(@PathVariable int id){
        try{
            UserDTO user = userServices.getUserById(id);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            return new ResponseData<>(HttpStatus.OK.value(), "GET SUCCESS", LocalDateTime.now(),userServices.getUserById(id));
        }
        catch (Exception e){
            return new ResponseData<>(HttpStatus.NOT_FOUND.value(), "e.getMessage()", LocalDateTime.now());
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseData<?> updateUser(@RequestBody UserDTO userDTO,@PathVariable int id){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "UPDATE SUCCESS", LocalDateTime.now(),userServices.updateDto(id,userDTO));

        }
        catch (EntityNotFoundException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
        }
    }



}
