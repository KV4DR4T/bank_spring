package com.example.bank_spring.rest;

import com.example.bank_spring.dto.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/loan")
public class LoanRestController {
    private final LoanService loanService;

    @Autowired
    public LoanRestController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping(value = "/loans/{loanId}")
    public ResponseEntity<?> getLoan(@PathVariable Long loanId, @RequestHeader("Authorization") String token) throws Exception {
        return loanService.getLoan(loanId,token);
    }

    @GetMapping(value = "/loans")
    public ResponseEntity<?> getLoans(@RequestHeader("Authorization") String token) throws Exception {
        return loanService.getLoans(token);
    }

    @PostMapping(value = "/create-loan")
    public void createLoan(@RequestHeader("Authorization") String token,
                           @RequestBody LoanCreationDto creationDto) throws Exception {
        loanService.createLoan(token,creationDto);
    }
}
