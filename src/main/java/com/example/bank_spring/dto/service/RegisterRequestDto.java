package com.example.bank_spring.dto.service;

import com.example.bank_spring.model.Role;
import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String birthDate;
    private String sex;
    private String email;
    private String password;

}
