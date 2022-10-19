package com.example.bank_spring.exception;

public class LoanNotFoundException extends Exception{
    public LoanNotFoundException(String message) {
        super(message);
    }
}
