package com.java1504.ManagerUsers.controller;


import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.model.Bank;
import com.java1504.ManagerUsers.dto.response.ResponseData;
import com.java1504.ManagerUsers.service.BankServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController()
public class BanksController {

    @Autowired
    BankServices bankServices;

    @PostMapping("/addBank/{id}")
    public ResponseData<?> addBank(@RequestBody Bank bank, @PathVariable int id) {
        try{
            Bank bank1 = bankServices.addBank(bank,id);
            return new ResponseData<>(HttpStatus.OK.value(), "Add bank for " + id +  " Success", LocalDateTime.now(),bank1);
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @GetMapping("/getBanks")
    public ResponseData<?> getAllBanks() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "get All Banks", LocalDateTime.now(),bankServices.getBanks());
        }
        catch (Exception e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
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


    @PostMapping("/transfer/{source}")
    public ResponseData<?> bankTransaction(@PathVariable String source, @RequestBody Map<String, Object> transactionDetails){
        try {
            String destination = (String) transactionDetails.get("destination");
            Number amountNumber = (Number) transactionDetails.get("amount");
            long amount = amountNumber.longValue();
            Bank success = bankServices.bankTransaction(source,destination,amount);
            return new ResponseData<>(HttpStatus.OK.value(),String.valueOf(success));
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }
    }


}
