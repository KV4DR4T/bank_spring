/*
Автор:
Дата: 08.12.2022
Версія: 1.0

Класс призначений для конфігурації роботи Spring Security, клас є нащадком класу WebSecurityConfigurerAdapter та імплементує його методи:
authenticationManagerBean() і configure()
*/


package com.example.bank_spring.config;

import com.example.bank_spring.security.jwt.JwtConfigurer;
import com.example.bank_spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final JwtConfigurer jwtConfigurer;
    private final JwtTokenProvider jwtTokenProvider;


    private static final String MANAGER_ENDPOINT="/manager/**";
    private static final String CHIEF_MANAGER_ENDPOINT="/api/chief_manager";
        private static final String LOGIN_ENDPOINT="/auth/login";
        private static final  String REGISTER_ENDPOINT ="/auth/register";



        /*Метод є конструктором що приймає об'єкт класу JwtTokenProvider*/
    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {

        this.jwtTokenProvider = jwtTokenProvider;

    }

    /*Метод є перевизначеним методом authenticationManagerBean() класу WebSecurityConfigurerAdapter*/

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /*Метод є перевизначеним методом configure() класу WebSecurityConfigurerAdapter*/
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable().cors().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().authorizeRequests().antMatchers(LOGIN_ENDPOINT,REGISTER_ENDPOINT).permitAll().
                anyRequest().authenticated().
                and().apply(new JwtConfigurer(jwtTokenProvider));
    }


}
