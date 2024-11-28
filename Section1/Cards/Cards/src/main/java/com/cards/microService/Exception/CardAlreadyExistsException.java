package com.cards.microService.Exception;

public class CardAlreadyExistsException extends RuntimeException {

    public CardAlreadyExistsException(String mobileNumber){
        super(mobileNumber);
    }
}
