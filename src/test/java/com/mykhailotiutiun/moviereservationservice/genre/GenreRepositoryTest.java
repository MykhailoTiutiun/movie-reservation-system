package com.mykhailotiutiun.moviereservationservice.genre;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.genres.datasource.GenreMapper;
import com.mykhailotiutiun.moviereservationservice.genres.datasource.GenreRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.genres.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

public class GenreRepositoryTest {

    private final GenreRepositoryImpl genreRepository;
    private final JdbcTemplate jdbcTemplate;

    public GenreRepositoryTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:database/schema.sql")
                .addScript("classpath:database/test_data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(dataSource);
        genreRepository = new GenreRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void findAll(){
        long existedId = 10L;
        Genre expectedGenre = Genre.builder()
                .id(existedId)
                .name("Test")
                .build();
        assertTrue(genreRepository.findAll().contains(expectedGenre));
    }
    @Test
    public void create(){
        Genre expectedGenre = Genre.builder()
                .name("createdTest")
                .build();
        genreRepository.create(expectedGenre);
        assertEquals(expectedGenre, jdbcTemplate.queryForObject("SELECT * FROM genres WHERE id = ?", new GenreMapper(), expectedGenre.getId()));

        assertThrows(AlreadyExistsException.class, () -> genreRepository.create(expectedGenre));
    }
    @Test
    public void update(){
        long notExistedId = 11L;
        Genre expectedGenre = Genre.builder()
                .id(12L)
                .name("updatedTest")
                .build();
        assertNotEquals(expectedGenre, jdbcTemplate.queryForObject("SELECT * FROM genres WHERE id = ?", new GenreMapper(), expectedGenre.getId()));
        genreRepository.update(expectedGenre);
        assertEquals(expectedGenre, jdbcTemplate.queryForObject("SELECT * FROM genres WHERE id = ?", new GenreMapper(), expectedGenre.getId()));

        expectedGenre.setId(notExistedId);
        assertThrows(NotFoundException.class, () -> genreRepository.update(expectedGenre));
    }
    @Test
    public void deleteById(){
        long notExistedId = 11L;
        long deleteId = 13L;
        genreRepository.deleteById(deleteId);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcTemplate.queryForObject("SELECT * FROM genres WHERE id = ?", new GenreMapper(), deleteId));

        assertThrows(NotFoundException.class, () -> genreRepository.deleteById(notExistedId));
    }
}
