package com.mykhailotiutiun.moviereservationservice.movie.config;

import com.mykhailotiutiun.moviereservationservice.movie.datasource.MovieRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.movie.domain.MovieRepository;
import com.mykhailotiutiun.moviereservationservice.movie.domain.MovieService;
import com.mykhailotiutiun.moviereservationservice.movie.domain.MovieServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MovieComponentConfig {

    @Bean
    public MovieService movieService(MovieRepository movieRepository){
        return new MovieServiceImpl(movieRepository);
    }

    @Bean
    public MovieRepository movieRepository(JdbcTemplate jdbcTemplate){
        return new MovieRepositoryImpl(jdbcTemplate);
    }
}
