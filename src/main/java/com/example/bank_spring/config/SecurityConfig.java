package com.example.bank_spring.config;

import com.example.bank_spring.model.Role;
import com.example.bank_spring.security.jwt.JwtConfigurer;
import com.example.bank_spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final JwtConfigurer jwtConfigurer;
    private final JwtTokenProvider jwtTokenProvider;


    private static final String MANAGER_ENDPOINT="/manager/**";
    private static final String CHIEF_MANAGER_ENDPOINT="/api/chief_manager";
        private static final String LOGIN_ENDPOINT="/login";
        private static final  String REGISTER_ENDPOINT ="/register";



    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {

        this.jwtTokenProvider = jwtTokenProvider;

    }
//    @Autowired
//    public SecurityConfig(JwtConfigurer jwtConfigurer, JwtTokenProvider jwtTokenProvider) {
//        this.jwtConfigurer = jwtConfigurer;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable().cors().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().authorizeRequests().antMatchers(LOGIN_ENDPOINT,REGISTER_ENDPOINT).permitAll().
//                antMatchers(MANAGER_ENDPOINT).hasAuthority(String.valueOf(Role.MANAGER.getAuthority())).
//                antMatchers(CHIEF_MANAGER_ENDPOINT).hasRole(String.valueOf(Role.CHIEF_MANAGER.getAuthority())).
                anyRequest().authenticated().
                and().apply(new JwtConfigurer(jwtTokenProvider));
    }


}
