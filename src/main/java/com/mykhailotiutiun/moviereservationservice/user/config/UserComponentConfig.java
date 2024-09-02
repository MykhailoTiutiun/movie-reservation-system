package com.mykhailotiutiun.moviereservationservice.user.config;

import com.mykhailotiutiun.moviereservationservice.user.datasource.UserRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.user.domain.*;
import com.mykhailotiutiun.moviereservationservice.user.security.PasswordEncoderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserComponentConfig {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider){
        return new UserServiceImpl(userRepository, passwordEncoder, jwtTokenProvider);
    }

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate){
        return new UserRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public PasswordEncoder passwordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        return new PasswordEncoderImpl(bCryptPasswordEncoder);
    }
}
