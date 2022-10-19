package com.example.bank_spring.security;

import com.example.bank_spring.model.User;
import com.example.bank_spring.repository.UserRepository;
import com.example.bank_spring.security.jwt.JwtUser;
import com.example.bank_spring.security.jwt.JwtUserFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

//    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    @Autowired
//    public JwtUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->  new Exception("e"));

        return JwtUserFactory.create(user);
    }
}
