package com.ravi.ecommerce.exceptions;


public class GiftCardDoesntExistException extends Exception{
    public GiftCardDoesntExistException(String message) {
        super(message);
    }
}