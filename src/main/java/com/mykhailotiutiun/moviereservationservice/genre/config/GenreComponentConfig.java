package com.mykhailotiutiun.moviereservationservice.genre.config;

import com.mykhailotiutiun.moviereservationservice.genre.datasource.GenreRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.genre.domain.GenreRepository;
import com.mykhailotiutiun.moviereservationservice.genre.domain.GenreService;
import com.mykhailotiutiun.moviereservationservice.genre.domain.GenreServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class GenreComponentConfig {

    @Bean
    public GenreService genreService(GenreRepository genreRepository){
        return new GenreServiceImpl(genreRepository);
    }

    @Bean
    public GenreRepository genreRepository(JdbcTemplate jdbcTemplate){
        return new GenreRepositoryImpl(jdbcTemplate);
    }
}
