package com.example.bank_spring.repository;

import com.example.bank_spring.model.Role;
import com.example.bank_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<List<User>> findAllByRole(Integer id);
    Optional<User> findById(Long id);

}
