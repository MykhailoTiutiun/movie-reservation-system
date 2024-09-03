package com.mykhailotiutiun.moviereservationservice.user.config;

import com.mykhailotiutiun.moviereservationservice.user.domain.JwtTokenProvider;
import com.mykhailotiutiun.moviereservationservice.user.token.JwtTokenCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtCreatorConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public JwtTokenProvider jwtTokenProvider(){
        return new JwtTokenCreator(jwtSecret);
    }
}
