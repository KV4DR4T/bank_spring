package com.example.bank_spring.repository;

import com.example.bank_spring.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    Optional<Loan> findByPersonId(Long personId);
    Optional<List<Loan>> findAllByPersonId(Long personId);
    Optional<Loan> findByIdAndPersonId(Long id, Long personId);
}
