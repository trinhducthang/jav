package com.java1504.ManagerUsers.controller;


import com.java1504.ManagerUsers.dto.BankDTO;
import com.java1504.ManagerUsers.response.ResponseData;
import com.java1504.ManagerUsers.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@RestController("/bank")
public class BanksController {

    UserServices userServices;

    @GetMapping("/dto")
    public ResponseData<?> getBankDto(){
        Set<BankDTO> bankDTOS = userServices.getBankDto();
        return new ResponseData<>(HttpStatus.OK.value(), "Get Success", LocalDateTime.now(),bankDTOS);
    }
}
