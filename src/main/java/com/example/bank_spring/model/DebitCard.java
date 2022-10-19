package com.example.bank_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "debit_cards")
@Data
public class DebitCard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "balance")
    private double balance;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "person_id")
    @JsonIgnore
    private Long personId;
}
