package com.java1504.ManagerUsers.controller;


import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.response.ResponseData;
import com.java1504.ManagerUsers.service.BankServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController()
public class BanksController {

    @Autowired
    BankServices bankServices;

    @PostMapping("/bankDto/{id}")
    public ResponseData<?> addBank(@RequestBody Bank bank, @PathVariable int id) {
        Bank bank1 = bankServices.addBank(bank,id);
        return new ResponseData<>(HttpStatus.OK.value(), "add Success", LocalDateTime.now(),bank1);
    }

    @GetMapping("/allBanks")
    public ResponseData<?> getAllBanks() {
        return new ResponseData<>(HttpStatus.OK.value(), "get All Banks", LocalDateTime.now(),bankServices.getBanks());
    }

}
