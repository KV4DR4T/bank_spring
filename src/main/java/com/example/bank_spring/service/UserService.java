package com.example.bank_spring.service;


import com.example.bank_spring.dto.AuthenticationRequestDto;
import com.example.bank_spring.dto.RegisterRequestDto;
import com.example.bank_spring.exception.InvalidInformationException;
import com.example.bank_spring.exception.UserNotFoundException;
import com.example.bank_spring.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    void register(RegisterRequestDto requestDto) throws InvalidInformationException;
    ResponseEntity<Map> login(AuthenticationRequestDto requestDto) throws UserNotFoundException;

    User findByEmail(String email) throws UserNotFoundException;

    void deleteById (Long id,String token) throws Exception;

    User updateById(Long id, String token, RegisterRequestDto requestDto) throws Exception;

    User showUser(String token) throws Exception;

    User showUserById(Long id,String token) throws Exception;

    ResponseEntity<?> showAllUsers(String token) throws Exception;

    void  createManager(String token, RegisterRequestDto requestDto) throws Exception;

}
