package com.cards.microService.Mapper;

import com.cards.microService.DTO.CardDto;
import com.cards.microService.Entity.Card;

public class CardMapper {

    public static CardDto mapToCardDto(CardDto cardDto, Card card){

       // cardDto.setCardId(card.getCardId());
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setTotalLimit(card.getTotalLimit());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setAvailableAmount(card.getAvailableAmount());

        return cardDto;

    }

    public static Card mapToCard(Card card,CardDto cardDto){
       // card.setCardId(cardDto.getCardId());
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        card.setTotalLimit(cardDto.getTotalLimit());
        return card;
    }

}
