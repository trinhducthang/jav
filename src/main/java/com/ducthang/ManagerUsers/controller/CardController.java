package com.ducthang.ManagerUsers.controller;

import com.ducthang.ManagerUsers.service.CardService;
import com.ducthang.ManagerUsers.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {


    private final CardService cardService;

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
