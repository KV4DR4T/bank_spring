/*
Автор:
Дата: 08.12.2022
Версія: 1.0

Клас CardNotFoundException є класом- нащадком  ErrorCodeException

*/

package com.example.bank_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFoundException extends ErrorCodeException{


    private static final int CODE=452;
    public CardNotFoundException( String message) {
        super(CODE, message,"452");
    }
}
