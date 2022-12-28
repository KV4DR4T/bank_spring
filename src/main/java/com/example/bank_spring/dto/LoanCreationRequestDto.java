/*
Автор:
Дата: 08.12.2022
Версія: 1.0

Клас LoanCreationRequestDto є класом побудованим на основі шаблону DTO(Data Transfer Object) який використовується для посилання запиту зі сторони клієнту
В даному випадку для посилання запиту на створення кредиту

*/


package com.example.bank_spring.dto;

import lombok.Data;

@Data
public class LoanCreationRequestDto {
    private double amount;
    private int period;
}
