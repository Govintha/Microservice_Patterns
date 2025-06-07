package com.eazybytes.card.card.service;

import com.eazybytes.card.card.dto.CardsDto;

public interface ICardsService {

    void createCard(String mobileNumber);

    CardsDto getCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);

}
