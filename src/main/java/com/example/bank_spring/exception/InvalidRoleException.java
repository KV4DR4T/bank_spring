package com.example.bank_spring.exception;

public class InvalidRoleException extends Exception{
    public InvalidRoleException(String message) {
        super(message);
    }
}
