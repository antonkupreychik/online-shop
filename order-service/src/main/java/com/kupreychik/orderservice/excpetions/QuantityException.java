package com.kupreychik.orderservice.excpetions;

public class QuantityException extends RuntimeException {

    public QuantityException(String message) {
        super(message);
    }
}
