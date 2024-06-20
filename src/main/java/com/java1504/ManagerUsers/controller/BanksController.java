package com.java1504.ManagerUsers.controller;


import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.response.ResponseData;
import com.java1504.ManagerUsers.service.BankServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController()
public class BanksController {

    @Autowired
    BankServices bankServices;

    @PostMapping("/addBank/{id}")
    public ResponseData<?> addBank(@RequestBody Bank bank, @PathVariable int id) {
        try{
            Bank bank1 = bankServices.addBank(bank,id);
            return new ResponseData<>(HttpStatus.OK.value(), "add Success", LocalDateTime.now(),bank1);
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @GetMapping("/getBanks")
    public ResponseData<?> getAllBanks() {
        return new ResponseData<>(HttpStatus.OK.value(), "get All Banks", LocalDateTime.now(),bankServices.getBanks());
    }

    @PutMapping("/updateBank/{id}")
    public ResponseData<?> updateBank(@RequestBody BankDTO bankDTO, @PathVariable int id) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Update successfully",LocalDateTime.now(),bankServices.updateBank(bankDTO, id));
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }
    }

}
