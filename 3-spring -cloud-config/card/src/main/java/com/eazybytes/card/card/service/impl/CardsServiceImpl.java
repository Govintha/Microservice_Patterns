package com.eazybytes.card.card.service.impl;

import com.eazybytes.card.card.constant.CardsConstants;
import com.eazybytes.card.card.dto.CardsDto;
import com.eazybytes.card.card.entity.Cards;
import com.eazybytes.card.card.exception.CardAlreadyExistsException;
import com.eazybytes.card.card.exception.ResourceNotFoundException;
import com.eazybytes.card.card.mapper.CardsMapper;
import com.eazybytes.card.card.repository.CardsRepository;
import com.eazybytes.card.card.service.ICardsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private final CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {

        Optional<Cards> byMobileNumber = cardsRepository.findByMobileNumber(mobileNumber);
        if(byMobileNumber.isPresent()){
            throw new CardAlreadyExistsException("Card Already Exists for this Mobile Number "+mobileNumber);
        }

        cardsRepository.save(createNewCard(mobileNumber));

    }

    @Override
    public CardsDto getCard(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card Not Found for give Number " + mobileNumber));

        return CardsMapper.mapToCardsDto(cards);

    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {

        Cards cards = cardsRepository.findByCardNumber(cardsDto.cardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card Not Found for give Number " + cardsDto.cardNumber()));
        Cards updatedCard = CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(updatedCard);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card Not Found for give Number " + mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
