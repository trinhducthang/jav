package com.ducthang.ManagerUsers.mapper;

import com.ducthang.ManagerUsers.dto.CardDTO;
import com.ducthang.ManagerUsers.model.Card;
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
