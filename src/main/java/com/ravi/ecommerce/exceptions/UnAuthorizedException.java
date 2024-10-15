package com.ravi.ecommerce.exceptions;

public class UnAuthorizedException extends Exception {
    public UnAuthorizedException(String message) {
        super(message);
    }
}