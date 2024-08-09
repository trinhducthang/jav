package com.java1504.ManagerUsers.mapper;

import com.java1504.ManagerUsers.dto.CardDTO;
import com.java1504.ManagerUsers.model.Card;
import org.springframework.stereotype.Component;

@Component("mapper")
public class CardMapper {
    public CardDTO cardtoCardDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardNumber(card.getCard_number());
        cardDTO.setCvv("***");
        cardDTO.setCreatedAt(card.getCreatedAt());
        cardDTO.setUsersId(card.getUsers().getId());
        return cardDTO;
    }

    Card CardDTOToCard(CardDTO cardDTO) {
        Card card = new Card();
        return card;
    }
}
