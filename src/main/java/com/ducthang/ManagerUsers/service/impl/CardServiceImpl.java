package com.ducthang.ManagerUsers.service.impl;

import com.ducthang.ManagerUsers.dto.CardDTO;
import com.ducthang.ManagerUsers.mapper.CardMapper;
import com.ducthang.ManagerUsers.model.Card;
import com.ducthang.ManagerUsers.model.Users;
import com.ducthang.ManagerUsers.repository.CardRepository;
import com.ducthang.ManagerUsers.repository.UsersRepository;
import com.ducthang.ManagerUsers.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    private final UsersRepository usersRepository;

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
