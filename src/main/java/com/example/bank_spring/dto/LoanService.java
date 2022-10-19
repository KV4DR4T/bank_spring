package com.example.bank_spring.dto;

import org.springframework.http.ResponseEntity;

public interface LoanService {
    void createLoan(String token, LoanCreationDto creationDto) throws Exception;
    ResponseEntity<?> getLoan(Long loanId,String token) throws Exception;
    ResponseEntity<?> getLoans(String token) throws Exception;
}
