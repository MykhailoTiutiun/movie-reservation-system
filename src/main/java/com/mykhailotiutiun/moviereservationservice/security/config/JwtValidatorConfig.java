package com.mykhailotiutiun.moviereservationservice.security.config;

import com.mykhailotiutiun.moviereservationservice.security.jwt.JwtAuthenticationFilter;
import com.mykhailotiutiun.moviereservationservice.security.jwt.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtValidatorConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenValidator jwtTokenValidator){
        return new JwtAuthenticationFilter(jwtTokenValidator);
    }

    @Bean
    public JwtTokenValidator jwtTokenValidator(){
        return new JwtTokenValidator(secret);
    }
}
