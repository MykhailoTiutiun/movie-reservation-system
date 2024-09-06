package com.mykhailotiutiun.moviereservationservice.genres.config;

import com.mykhailotiutiun.moviereservationservice.genres.datasource.GenreRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.genres.domain.GenreRepository;
import com.mykhailotiutiun.moviereservationservice.genres.domain.GenreService;
import com.mykhailotiutiun.moviereservationservice.genres.domain.GenreServiceImpl;
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
