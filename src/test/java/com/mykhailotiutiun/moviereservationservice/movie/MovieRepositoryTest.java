package com.mykhailotiutiun.moviereservationservice.movie;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.movie.datasource.MovieMapper;
import com.mykhailotiutiun.moviereservationservice.movie.datasource.MovieRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.movie.domain.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

public class MovieRepositoryTest {

    private final MovieRepositoryImpl movieRepository;
    private final JdbcTemplate jdbcTemplate;

    public MovieRepositoryTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:database/schema.sql")
                .addScript("classpath:database/test_data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(dataSource);
        movieRepository = new MovieRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void findByIdTest() {
        long existedId = 10L;
        long notExistedId = 11L;
        Movie expectedMovie = Movie.builder()
                .id(existedId)
                .title("Test")
                .description("Test")
                .build();
        assertEquals(expectedMovie, movieRepository.findById(existedId).orElseThrow(NotFoundException::new));
        assertTrue(movieRepository.findById(notExistedId).isEmpty());
    }

    @Test
    public void findAllTest() {
        long existedId = 10L;
        Movie expectedMovie = Movie.builder()
                .id(existedId)
                .title("Test")
                .description("Test")
                .build();
        assertTrue(movieRepository.findAll().contains(expectedMovie));
    }

    @Test
    public void createTest() {
        Movie expectedMovie = Movie.builder()
                .title("createdTest")
                .description("createdTest")
                .build();
        movieRepository.create(expectedMovie);
        assertEquals(expectedMovie, jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", new MovieMapper(), expectedMovie.getId()));

        assertThrows(AlreadyExistsException.class, () -> movieRepository.create(expectedMovie));
    }

    @Test
    public void updateTest() {
        Movie expectedMovie = Movie.builder()
                .id(12L)
                .title("updatedTest")
                .description("updatedTest")
                .build();
        assertNotEquals(expectedMovie, jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", new MovieMapper(), expectedMovie.getId()));
        movieRepository.update(expectedMovie);
        assertEquals(expectedMovie, jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", new MovieMapper(), expectedMovie.getId()));
    }

    @Test
    public void deleteByIdTest() {
        long deleteId = 12L;
        movieRepository.deleteById(deleteId);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", new MovieMapper(), deleteId));
    }
}
