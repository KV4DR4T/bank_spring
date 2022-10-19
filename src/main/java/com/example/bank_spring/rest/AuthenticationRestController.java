package com.example.bank_spring.rest;

import com.example.bank_spring.dto.*;
import com.example.bank_spring.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationRestController {

    private final UserService userService;
    private final CardService cardService;
    private final LoanService loanService;




    public AuthenticationRestController(UserService userService, CardService cardService,
                                        LoanService loanService) {
        this.userService = userService;
        this.cardService = cardService;
        this.loanService = loanService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map> login(@RequestBody AuthenticationRequestDto requestDto) throws Exception {
        return userService.login(requestDto);
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody RegisterRequestDto requestDto){
        userService.register(requestDto);
    }


    @GetMapping(value = "/showUser")
    public User showUser(@RequestHeader("Authorization") String token) throws Exception {
        return userService.showUser(token);
    }

}
