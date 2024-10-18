package com.ravi.ecommerce.exceptions;


public class GiftCardExpiredException extends Exception{
    public GiftCardExpiredException(String message) {
        super(message);
    }
}