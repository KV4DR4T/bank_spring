package com.example.bank_spring.security.jwt;

import com.example.bank_spring.model.Role;
import com.example.bank_spring.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static  JwtUser create(User user){
        return new JwtUser(user.getId(), user.getName(),user.getBirthDate(),
                user.getSex(),user.getEmail(),user.getPassword(),
                mapToGrantedAuthorities(user.getRole())
                );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role role){
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getAuthority()));
    }

}
