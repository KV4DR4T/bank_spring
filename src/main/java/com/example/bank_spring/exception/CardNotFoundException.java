package com.example.bank_spring.exception;

public class CardNotFoundException extends Exception{
    public CardNotFoundException(String message) {
        super(message);
    }
}