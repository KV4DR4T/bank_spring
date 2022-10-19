package com.example.bank_spring.repository;


import com.example.bank_spring.model.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<DebitCard,Long> {
    Optional<DebitCard> findDebitCardByPersonId(Long personId);
    Optional<List<DebitCard>> findAllByPersonId(Long personID);
}
