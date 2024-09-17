package com.ducthang.ManagerUsers.controller;


import com.ducthang.ManagerUsers.dto.UserDTO;
import com.ducthang.ManagerUsers.dto.response.ResponseData;
import com.ducthang.ManagerUsers.model.Users;
import com.ducthang.ManagerUsers.service.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
                return ResponseData.builder()
                    .status(HttpStatus.OK.value())
                    .message("User added successfully")
                    .data(userServices.addUser(userDTO))
                    .build();
        }
        catch (Exception e){
            return ResponseData.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build();
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


    @GetMapping("/api/user/{username}")
    public Optional<Users> getUserByUsername(@PathVariable String username) {
        return userServices.findByUsername(username);
    }

    @GetMapping("/getUser/{number}")
    public String getUserByNumber(@PathVariable String number){
        try {
            return userServices.getNameByNumber(number);
        }
        catch (RuntimeException e){
            return e.getMessage();
        }
    }

    @GetMapping("/findAll")
    public List<Users> findAll(){
        return userServices.findAll();
    }

}
