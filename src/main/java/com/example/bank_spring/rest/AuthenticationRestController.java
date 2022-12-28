package com.example.bank_spring.rest;

import com.example.bank_spring.dto.*;
import com.example.bank_spring.exception.InvalidInformationException;
import com.example.bank_spring.model.User;
import com.example.bank_spring.service.CardService;
import com.example.bank_spring.service.LoanService;
import com.example.bank_spring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationRestController {

    private final UserService userService;

    public AuthenticationRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map> login(@RequestBody AuthenticationRequestDto requestDto) throws Exception {
        return userService.login(requestDto);
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody RegisterRequestDto requestDto) throws InvalidInformationException {
        userService.register(requestDto);
    }


    @GetMapping(value = "/showUser")
    public User showUser(@RequestHeader("Authorization") String token) throws Exception {
        return userService.showUser(token);
    }

}
