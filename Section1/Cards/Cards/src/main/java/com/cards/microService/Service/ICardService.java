package com.cards.microService.Service;

import com.cards.microService.DTO.CardDto;

public interface ICardService {


    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createCard(String mobileNumber);

      /**
     *
     * @param mobileNumber - Input mobile Number
     *  @return Card Details based on a given mobileNumber
     */
    CardDto fetchData(String mobileNumber);


    /**
     *
     * @param cardDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateCard(CardDto cardDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    boolean dateleCard(String mobileNumber);

    // createCardDetails post
//    public CardDto createCardDetails(CardDto cardDto);
//
//    // get id
//    public CardDto getCardId(Long cardId);
//
//    //modify te card details
//    public CardDto updateCardData(CardDto cardDto, Long cardId);
//
//   // ..delete cardId
//    public String deleteCardId(Long cardId);

}
