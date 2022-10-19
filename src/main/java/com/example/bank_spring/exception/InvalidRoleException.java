package com.example.bank_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRoleException extends ErrorCodeException{
    private static final int CODE=456;
    public InvalidRoleException( String message) {
        super(CODE, message,"456");
    }
}
