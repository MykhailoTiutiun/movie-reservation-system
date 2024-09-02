package com.mykhailotiutiun.moviereservationservice.showtime.config;

import com.mykhailotiutiun.moviereservationservice.showtime.datasource.ShowtimeRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeRepository;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeService;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeServiceImpl;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ToShowtimeSeatsCloner;
import com.mykhailotiutiun.moviereservationservice.showtime.dto.ShowtimeResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ShowtimeComponentConfig {

    @Bean
    public ShowtimeService showtimeService(ShowtimeRepository showtimeRepository, ToShowtimeSeatsCloner showtimeSeatsCloner){
        return new ShowtimeServiceImpl(showtimeRepository, showtimeSeatsCloner);
    }

    @Bean
    public ShowtimeRepository showtimeRepository(JdbcTemplate jdbcTemplate){
        return new ShowtimeRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public ShowtimeResponseMapper showtimeResponseMapper(){
        return new ShowtimeResponseMapper();
    }
}
