package com.java1504.ManagerUsers.service;

import com.java1504.ManagerUsers.dto.CardDTO;
import com.java1504.ManagerUsers.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    public CardDTO addCard( int id);

    public List<Card> getCard(int id);
}
