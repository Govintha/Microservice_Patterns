package com.eazybytes.card.card.mapper;

import com.eazybytes.card.card.dto.CardsDto;
import com.eazybytes.card.card.entity.Cards;

public class CardsMapper {

    private CardsMapper(){}

    public static CardsDto mapToCardsDto(Cards cards){

        CardsDto  cardsDto=new CardsDto(
                cards.getMobileNumber(),
                cards.getCardNumber(),
                cards.getCardType(),
                cards.getTotalLimit(),
                cards.getAmountUsed(),
                cards.getAvailableAmount()
        );
        return cardsDto;
    }

    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.cardNumber());
        cards.setCardType(cardsDto.cardType());
        cards.setMobileNumber(cardsDto.mobileNumber());
        cards.setTotalLimit(cardsDto.totalLimit());
        cards.setAvailableAmount(cardsDto.availableAmount());
        cards.setAmountUsed(cardsDto.amountUsed());
        return cards;
    }
}
