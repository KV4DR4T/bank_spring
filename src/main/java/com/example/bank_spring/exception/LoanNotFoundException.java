package com.example.bank_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoanNotFoundException extends ErrorCodeException{
    private static final int CODE=453;
    public LoanNotFoundException( String message) {
        super(CODE, message,"453");
    }
}
