package com.ravi.ecommerce.exceptions;

public class ProductInStockException extends Exception {
    public ProductInStockException(String message) {
        super(message);
    }
}