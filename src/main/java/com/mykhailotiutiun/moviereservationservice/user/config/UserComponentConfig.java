package com.mykhailotiutiun.moviereservationservice.user.config;

import com.mykhailotiutiun.moviereservationservice.user.datasource.UserRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.user.datasource.VerificationTokenRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.user.domain.*;
import com.mykhailotiutiun.moviereservationservice.user.security.PasswordEncoderImpl;
import com.mykhailotiutiun.moviereservationservice.user.token.VerificationTokenCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserComponentConfig {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, VerificationTokenProvider verificationTokenProvider, VerificationTokenRepository verificationTokenRepository, MailSender mailSender){
        return new UserServiceImpl(userRepository, passwordEncoder, jwtTokenProvider, verificationTokenProvider, verificationTokenRepository, mailSender);
    }

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate){
        return new UserRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public VerificationTokenRepositoryImpl tokenRepository(JdbcTemplate jdbcTemplate){
        return new VerificationTokenRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public VerificationTokenProvider verificationTokenProvider(){
        return new VerificationTokenCreator();
    }
    @Bean
    public PasswordEncoder passwordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        return new PasswordEncoderImpl(bCryptPasswordEncoder);
    }
}
