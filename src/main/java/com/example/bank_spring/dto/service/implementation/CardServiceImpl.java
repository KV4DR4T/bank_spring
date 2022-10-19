package com.example.bank_spring.dto.service.implementation;

import com.example.bank_spring.dto.service.CardService;
import com.example.bank_spring.dto.service.UserService;
import com.example.bank_spring.exception.CardNotFoundException;
import com.example.bank_spring.model.DebitCard;
import com.example.bank_spring.model.User;
import com.example.bank_spring.repository.CardRepository;
import com.example.bank_spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.cardRepository = cardRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public void createCard(String token) throws Exception {
        DebitCard card = new DebitCard();
        card.setBalance(0.0);
        String cvv = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            cvv += "" + random.nextInt(9);
        }
        String number = "5375 4141";
        for (int i = 0; i < 2; i++) {
            number += " ";
            for (int j = 0; j < 4; j++) {
                number += random.nextInt(9);
            }
        }
        card.setCvv(cvv);
        card.setNumber(number);
        Date date = new Date();
        String month = date.getMonth() + 1 + "";
        String year = date.getYear() - 94 + "";
        card.setEndDate(month + "/" + year);
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user = userService.findByEmail(email);
        card.setPersonId(user.getId());
        cardRepository.save(card);
    }

    @Override
    public ResponseEntity<?> getCard(Long cardId, String token) throws Exception {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user= userService.findByEmail(email);
        Long id = user.getId();
        List<DebitCard> cards=cardRepository.findAllByPersonId(id).orElseThrow(()->
                new CardNotFoundException("User doesn't have card with id: "+cardId));
        //TODO: as loan
        for(DebitCard c: cards){
            if(c.getId().compareTo(cardId)==0){
                return ResponseEntity.ok(c);
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getCards(String token) throws Exception {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user = userService.findByEmail(email);
        Long id = user.getId();
        List<DebitCard> cards = cardRepository.findAllByPersonId(id).orElseThrow(()->
                new CardNotFoundException("User has no cards"));
        return ResponseEntity.ok(cards);
    }

}
