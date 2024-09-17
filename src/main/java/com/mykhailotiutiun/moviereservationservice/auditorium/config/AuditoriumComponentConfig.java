package com.mykhailotiutiun.moviereservationservice.auditorium.config;

import com.mykhailotiutiun.moviereservationservice.auditorium.datasource.AuditoriumRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumRepository;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumService;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AuditoriumComponentConfig {

    @Bean
    public AuditoriumService auditoriumService(AuditoriumRepository auditoriumRepository){
        return new AuditoriumServiceImpl(auditoriumRepository);
    }

    @Bean
    public AuditoriumRepository auditoriumRepository(JdbcTemplate jdbcTemplate){
        return new AuditoriumRepositoryImpl(jdbcTemplate);
    }
}
