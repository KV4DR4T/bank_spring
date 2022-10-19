package com.example.bank_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "loans")
@Data
public class Loan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(name = "sum")
    private double sum;
    @Column(name = "date")
    private String date;
    @Column(name = "percent")
    private int percent;
    @Column(name = "period_in_month")
    private int period;
    @Column(name = "monthly_payment")
    private double monthlyPayment;
    @Column(name = "person_id")
    @JsonIgnore
    private Long personId;
}
