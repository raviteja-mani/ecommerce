package com.ravi.ecommerce.exceptions;

public class OrderDoesNotBelongToUserException extends Exception {
    public OrderDoesNotBelongToUserException(String message) {
        super(message);
    }
}
