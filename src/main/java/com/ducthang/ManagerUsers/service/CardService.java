package com.ducthang.ManagerUsers.service;

import com.ducthang.ManagerUsers.dto.CardDTO;
import com.ducthang.ManagerUsers.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    public CardDTO addCard( int id);

    public List<Card> getCard(int id);
}
