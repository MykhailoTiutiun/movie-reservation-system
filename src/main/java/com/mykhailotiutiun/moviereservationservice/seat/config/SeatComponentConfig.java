package com.mykhailotiutiun.moviereservationservice.seat.config;

import com.mykhailotiutiun.moviereservationservice.seat.datasource.SeatRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatRepository;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatService;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class SeatComponentConfig {

    @Bean
    public SeatService seatService(SeatRepository seatRepository){
        return new SeatServiceImpl(seatRepository);
    }

    @Bean
    public SeatRepository seatRepository(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate){
        return new SeatRepositoryImpl(jdbcTemplate, transactionTemplate);
    }
}
