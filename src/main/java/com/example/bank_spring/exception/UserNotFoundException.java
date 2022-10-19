package com.example.bank_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ErrorCodeException{
    private static final int CODE=454;
    public UserNotFoundException( String message) {
        super(CODE, message,"454");
    }
}
