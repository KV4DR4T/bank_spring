package com.example.bank_spring.dto;

import lombok.Data;

@Data
public class LoanCreationDto {
    private double amount;
    private int period;
}
