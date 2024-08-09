package com.java1504.ManagerUsers.controller;

import com.java1504.ManagerUsers.dto.CardDTO;
import com.java1504.ManagerUsers.dto.response.ResponseData;
import com.java1504.ManagerUsers.model.Card;
import com.java1504.ManagerUsers.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/{id}")
    public ResponseData<?> addCard(@PathVariable int id) {
            try {
                return new ResponseData<>(HttpStatus.OK.value(), "Add card successfully", LocalDateTime.now(), cardService.addCard(id));
            }
            catch (Exception e) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getCard(@PathVariable int id) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get card successfully", LocalDateTime.now(), cardService.getCard(id));
        }
        catch (Exception e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
