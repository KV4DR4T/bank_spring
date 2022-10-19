package com.example.bank_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInformationException extends ErrorCodeException{
    private static final int CODE=455;
    public InvalidInformationException( String message) {
        super(CODE, message,"455");
    }
}
