package com.example.bank_spring.dto.service;

import org.springframework.http.ResponseEntity;

public interface CardService {
     void createCard(String token) throws Exception;
     ResponseEntity<?> getCard(Long cardId,String token) throws Exception;
     ResponseEntity<?> getCards(String token) throws Exception;
}
