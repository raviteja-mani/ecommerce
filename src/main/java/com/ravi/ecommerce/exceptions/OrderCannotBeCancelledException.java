package com.ravi.ecommerce.exceptions;


public class OrderCannotBeCancelledException extends Exception {
    public OrderCannotBeCancelledException(String message) {
        super(message);
    }
}
