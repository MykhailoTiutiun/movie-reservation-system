package com.mykhailotiutiun.moviereservationservice.showtime;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.showtime.datasource.ShowtimeMapper;
import com.mykhailotiutiun.moviereservationservice.showtime.datasource.ShowtimeRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.Showtime;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ShowtimeRepositoryTest {

    private final ShowtimeRepositoryImpl showtimeRepository;
    private final JdbcTemplate jdbcTemplate;

    public ShowtimeRepositoryTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:database/schema.sql")
                .addScript("classpath:database/test_data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(dataSource);
        showtimeRepository = new ShowtimeRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void findAllByAuditoriumIdTest() {
        long auditoriumId = 10L;
        long existedId = 10L;
        Showtime expectedShowtime = Showtime.builder()
                .id(existedId)
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .auditoriumId(auditoriumId)
                .build();
        assertTrue(showtimeRepository.findAllByAuditoriumId(auditoriumId).contains(expectedShowtime));
    }

    @Test
    public void findAllByAuditoriumIdAndDateTest() {
        long auditoriumId = 10L;
        LocalDate date = LocalDate.of(2024, 8, 31);
        long existedId = 10L;
        Showtime expectedShowtime = Showtime.builder()
                .id(existedId)
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .auditoriumId(auditoriumId)
                .build();
        assertTrue(showtimeRepository.findAllByAuditoriumIdAndDate(auditoriumId, date).contains(expectedShowtime));
    }

    @Test
    public void findByIdTest() {
        long existedId = 10L;
        long notExistedId = 11L;
        Showtime expectedShowtime = Showtime.builder()
                .id(existedId)
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .auditoriumId(10L)
                .build();
        assertEquals(expectedShowtime, showtimeRepository.findById(existedId).orElseThrow(NotFoundException::new));
        assertTrue(showtimeRepository.findById(notExistedId).isEmpty());
    }

    @Test
    public void createTest() {
        long auditoriumId = 10L;
        Showtime expectedShowtime = Showtime.builder()
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(12, 10))
                .endTime(LocalTime.of(14, 0))
                .auditoriumId(auditoriumId)
                .build();
        showtimeRepository.create(expectedShowtime);
        assertEquals(expectedShowtime, jdbcTemplate.queryForObject("SELECT * FROM showtimes WHERE id = ?", new ShowtimeMapper(), expectedShowtime.getId()));

        assertThrows(AlreadyExistsException.class, () -> showtimeRepository.create(expectedShowtime));

        Showtime collisionShowtime1 = Showtime.builder()
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(11, 0))
                .endTime(LocalTime.of(14, 0))
                .auditoriumId(auditoriumId)
                .build();
        Showtime collisionShowtime2 = Showtime.builder()
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(11, 0))
                .auditoriumId(auditoriumId)
                .build();
        Showtime collisionShowtime3 = Showtime.builder()
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(12, 0))
                .endTime(LocalTime.of(14, 0))
                .auditoriumId(auditoriumId)
                .build();

        assertThrows(AlreadyExistsException.class, () -> showtimeRepository.create(collisionShowtime1));
        assertThrows(AlreadyExistsException.class, () -> showtimeRepository.create(collisionShowtime2));
        assertThrows(AlreadyExistsException.class, () -> showtimeRepository.create(collisionShowtime3));
    }

    @Test
    public void updateTest(){
        long notExistedId = 11L;
        Showtime expectedShowtime = Showtime.builder()
                .id(13L)
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(7, 0))
                .endTime(LocalTime.of(9, 0))
                .auditoriumId(10L)
                .build();
        assertNotEquals(expectedShowtime, jdbcTemplate.queryForObject("SELECT * FROM showtimes WHERE id = ?", new ShowtimeMapper(), expectedShowtime.getId()));
        showtimeRepository.update(expectedShowtime);
        assertEquals(expectedShowtime, jdbcTemplate.queryForObject("SELECT * FROM showtimes WHERE id = ?", new ShowtimeMapper(), expectedShowtime.getId()));

        Showtime collisionShowtime = Showtime.builder()
                .id(12L)
                .date(LocalDate.of(2024, 8, 31))
                .startTime(LocalTime.of(11, 0))
                .endTime(LocalTime.of(14, 0))
                .build();
        assertThrows(AlreadyExistsException.class, () -> showtimeRepository.update(collisionShowtime));

        expectedShowtime.setId(notExistedId);
        assertThrows(NotFoundException.class, () -> showtimeRepository.update(expectedShowtime));
    }

    @Test
    public void deleteByIdTest() {
        long notExistedId = 11L;
        long deleteId = 12L;
        showtimeRepository.deleteById(deleteId);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcTemplate.queryForObject("SELECT * FROM showtimes WHERE id = ?", new ShowtimeMapper(), deleteId));

        assertThrows(NotFoundException.class, () -> showtimeRepository.deleteById(notExistedId));
    }
}
