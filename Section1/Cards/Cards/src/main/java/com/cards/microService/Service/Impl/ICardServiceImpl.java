package com.cards.microService.Service.Impl;

import com.cards.microService.Contants.CardsConstants;
import com.cards.microService.DTO.CardDto;
import com.cards.microService.Entity.Card;
import com.cards.microService.Exception.CardAlreadyExistsException;
import com.cards.microService.Exception.ResourceNotFoundException;
import com.cards.microService.Mapper.CardMapper;
import com.cards.microService.Repository.CardRepository;
import com.cards.microService.Service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ICardServiceImpl implements ICardService {

    CardRepository cardRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {

        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent()){
           throw new CardAlreadyExistsException(" card already registered "+mobileNumber);
        }

        Card card = cardRepository.save(createNewCard(mobileNumber));
    }
    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    public Card createNewCard(String mobileNumber){
        Card card = new Card();
        long randomCardNumber=10000000+new Random().nextLong(9000000000L);

        card.setCardNumber(Long.toString(randomCardNumber));
        card.setMobileNumber(mobileNumber);
        card.setCardType(CardsConstants.CREDIT_CARD);
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return card;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardDto fetchData(String mobileNumber) {
        Card card= cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("cards", "mobileNumber",
                        mobileNumber));
        return CardMapper.mapToCardDto(new CardDto(), card);
       // return null;
    }

    /**
     * @param cardDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card= cardRepository.findByCardNumber(cardDto.getCardNumber())
                .orElseThrow(()-> new ResourceNotFoundException
                        ("cardNumber", "card",cardDto.getCardNumber()));
        CardMapper.mapToCard(card, cardDto);
        cardRepository.save(card);
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean dateleCard(String mobileNumber) {

        Card deleteCard= cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException
                        ("card", "mobileNumber", mobileNumber));

       cardRepository.deleteById(deleteCard.getCardId());

        return true;
    }


//    @Override
//    public CardDto createCardDetails(CardDto cardDto) {
//        return null;
//    }
//
//    @Override
//    public CardDto getCardId(Long cardId) {
//        //Exception
//        return null;
//    }
//
//    @Override
//    public CardDto updateCardData(CardDto cardDto, Long cardId) {
//        //
//        return null;
//    }
//
//    @Override
//    public String deleteCardId(Long cardId) {
//
//        return "";
//    }
}
