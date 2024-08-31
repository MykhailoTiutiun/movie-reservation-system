package com.mykhailotiutiun.moviereservationservice.auditorium;

import com.mykhailotiutiun.moviereservationservice.auditorium.datasource.AuditoriumMapper;
import com.mykhailotiutiun.moviereservationservice.auditorium.datasource.AuditoriumRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.exceptions.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

public class AuditoriumRepositoryTest {

    private final AuditoriumRepositoryImpl auditoriumRepository;
    private final JdbcTemplate jdbcTemplate;

    public AuditoriumRepositoryTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:database/schema.sql")
                .addScript("classpath:database/test_data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(dataSource);
        auditoriumRepository = new AuditoriumRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void findByIdTest() {
        long existedId = 10L;
        long notExistedId = 11L;
        Auditorium expectedAuditorium = Auditorium.builder()
                .id(existedId)
                .name("Test")
                .description("Test")
                .build();
        assertEquals(expectedAuditorium, auditoriumRepository.findById(existedId).orElseThrow(NotFoundException::new));
        assertTrue(auditoriumRepository.findById(notExistedId).isEmpty());
    }

    @Test
    public void findAllByMovieIdTest() {
        long movieId = 10L;
        long existedId = 10L;
        Auditorium expectedAuditorium = Auditorium.builder()
                .id(existedId)
                .name("Test")
                .description("Test")
                .build();
        assertTrue(auditoriumRepository.findAllByMovieId(movieId).contains(expectedAuditorium));
    }

    @Test
    public void createTest() {
        long movieId = 10L;
        Auditorium expectedAuditorium = Auditorium.builder()
                .name("createdTest")
                .description("createdTest")
                .build();
        auditoriumRepository.create(expectedAuditorium, movieId);
        assertEquals(expectedAuditorium, jdbcTemplate.queryForObject("SELECT * FROM auditoriums WHERE id = ?", new AuditoriumMapper(), expectedAuditorium.getId()));

        assertThrows(AlreadyExistsException.class, () -> auditoriumRepository.create(expectedAuditorium, movieId));
    }
}
