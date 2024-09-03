package com.mykhailotiutiun.moviereservationservice.auditorium.config;

import com.mykhailotiutiun.moviereservationservice.auditorium.datasource.AuditoriumRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumRepository;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumService;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumServiceImpl;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.ToAuditoriumSeatsCloner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AuditoriumComponentConfig {

    @Bean
    public AuditoriumService auditoriumService(AuditoriumRepository auditoriumRepository, ToAuditoriumSeatsCloner toAuditoriumSeatsCloner){
        return new AuditoriumServiceImpl(auditoriumRepository, toAuditoriumSeatsCloner);
    }

    @Bean
    public AuditoriumRepository auditoriumRepository(JdbcTemplate jdbcTemplate){
        return new AuditoriumRepositoryImpl(jdbcTemplate);
    }
}
