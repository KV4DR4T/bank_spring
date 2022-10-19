package com.example.bank_spring.rest;

import com.example.bank_spring.dto.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card")
public class CardRestController {
    private final CardService cardService;

    @Autowired
    public CardRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(value = "/createCard")
    public void createCard(@RequestHeader(value = "Authorization" ) String token) throws Exception {
        cardService.createCard(token);
    }

    @GetMapping(value = "/debitCards/{cardId}")
    public ResponseEntity<?> getCard(@PathVariable Long cardId, @RequestHeader("Authorization") String token) throws Exception {
        return cardService.getCard(cardId,token);
    }

    @GetMapping(value="/debitCards")
    public ResponseEntity<?> getCards(@RequestHeader("Authorization") String token) throws Exception {
        return cardService.getCards(token);
    }
}
