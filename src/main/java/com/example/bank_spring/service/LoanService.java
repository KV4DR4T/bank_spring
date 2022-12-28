package com.example.bank_spring.service;

import com.example.bank_spring.dto.LoanCreationRequestDto;
import org.springframework.http.ResponseEntity;

public interface LoanService {
    void createLoan(String token, LoanCreationRequestDto creationDto) throws Exception;
    ResponseEntity<?> getLoan(Long loanId,String token) throws Exception;
    ResponseEntity<?> getLoans(String token) throws Exception;
}
