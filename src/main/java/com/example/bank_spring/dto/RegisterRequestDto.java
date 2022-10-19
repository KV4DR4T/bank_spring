package com.example.bank_spring.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String birthDate;
    private String sex;
    private String email;
    private String password;

}
