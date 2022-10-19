package com.example.bank_spring.dto.service;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
