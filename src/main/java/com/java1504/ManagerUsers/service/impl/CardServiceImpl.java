package com.java1504.ManagerUsers.service.impl;

import com.java1504.ManagerUsers.dto.CardDTO;
import com.java1504.ManagerUsers.mapper.CardMapper;
import com.java1504.ManagerUsers.model.Card;
import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.repository.CardRepository;
import com.java1504.ManagerUsers.repository.UsersRepository;
import com.java1504.ManagerUsers.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public CardDTO addCard( int id) {
        Users user = usersRepository.findById(id).orElseThrow(() ->new RuntimeException("user not found"));
        Card card = new Card();
        card.setUsers(user);
        CardDTO cardDTO = cardMapper.cardtoCardDTO(card);
        cardRepository.save(card);
        return cardDTO;
    }

    @Override
    public List<Card> getCard(int id) {
        List<Card> cards = cardRepository.findByUsers_Id(id);
        return cards;
    }
}
