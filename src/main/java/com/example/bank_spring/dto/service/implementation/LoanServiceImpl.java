package com.example.bank_spring.dto.service.implementation;

import com.example.bank_spring.dto.service.LoanCreationDto;
import com.example.bank_spring.dto.service.LoanService;
import com.example.bank_spring.dto.service.UserService;
import com.example.bank_spring.exception.LoanNotFoundException;
import com.example.bank_spring.model.Loan;
import com.example.bank_spring.model.User;
import com.example.bank_spring.repository.LoanRepository;
import com.example.bank_spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.loanRepository = loanRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public void createLoan(String token, LoanCreationDto creationDto) throws Exception {
        Loan loan = new Loan();
        Date date = new Date();
        String month = date.getMonth()+1+"";
        String year = date.getYear()-100+"";
        loan.setDate(month+" / "+year);
        loan.setPercent(10);
        loan.setSum(creationDto.getAmount());
        loan.setPeriod(creationDto.getPeriod());
        loan.setMonthlyPayment((creationDto.getAmount()*1.1)/creationDto.getPeriod());
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user = userService.findByEmail(email);
        loan.setPersonId(user.getId());
        loanRepository.save(loan);
    }

    @Override
    public ResponseEntity<?> getLoan(Long loanId, String token) throws Exception {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user= userService.findByEmail(email);
        Long id = user.getId();
        List<Loan> loans = loanRepository.findAllByPersonId(id).orElseThrow(()->
                new LoanNotFoundException("User doesn't have loan with id: "+loanId));
        Map<String,String> status = new HashMap<>();
        for(Loan l: loans){
            if(l.getId().compareTo(loanId)==0){
                return ResponseEntity.ok(l);
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getLoans(String token) throws Exception {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user= userService.findByEmail(email);
        Long id = user.getId();
        List<Loan> loans = loanRepository.findAllByPersonId(id).orElseThrow(()->
                new LoanNotFoundException("User has no loans"));
        return ResponseEntity.ok(loans);
    }
}
