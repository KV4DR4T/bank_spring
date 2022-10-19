package com.example.bank_spring.model;

//import com.example.bank_spring.security.RoleConverter;
//import com.example.bank_spring.security.StatusConverter;
import com.example.bank_spring.security.RoleConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "people")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private String birthDate;
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "role")
    @Convert(converter = RoleConverter.FieldConverter.class)
    @JsonIgnore
    private Role role;
//    @Column(name = "status")
//    @Convert(converter = StatusConverter.FieldConverter.class)
//    private Status status;
}
