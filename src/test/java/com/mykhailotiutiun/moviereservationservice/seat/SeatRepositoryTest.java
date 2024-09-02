package com.mykhailotiutiun.moviereservationservice.seat;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.exception.ReservationException;
import com.mykhailotiutiun.moviereservationservice.seat.datasource.SeatMapper;
import com.mykhailotiutiun.moviereservationservice.seat.datasource.SeatRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.seat.domain.Seat;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeatRepositoryTest {

    private final SeatRepositoryImpl seatRepository;
    private final JdbcTemplate jdbcTemplate;

    public SeatRepositoryTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:database/schema.sql")
                .addScript("classpath:database/test_data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(dataSource);
        seatRepository = new SeatRepositoryImpl(jdbcTemplate, new TransactionTemplate(new DataSourceTransactionManager(dataSource)));
    }

    @Test
    public void findAllByShowtimeIdTest() {
        long existedId = 10L;
        long showtimeId = 10L;
        Seat expectedSeat = Seat.builder()
                .id(existedId)
                .name("Test")
                .availability(true)
                .build();
        assertTrue(seatRepository.findAllByShowtimeId(showtimeId).contains(expectedSeat));
    }

    @Test
    public void findAllByAuditoriumIdTest() {
        long existedId = 10L;
        long auditoriumId = 10L;
        Seat expectedSeat = Seat.builder()
                .id(existedId)
                .name("Test")
                .availability(true)
                .build();
        assertTrue(seatRepository.findAllByAuditoriumId(auditoriumId).contains(expectedSeat));
    }

    @Test
    public void findAllByUserIdTest() {
        long existedId = 10L;
        long userId = 10L;
        Seat expectedSeat = Seat.builder()
                .id(existedId)
                .name("Test")
                .availability(true)
                .build();
        assertTrue(seatRepository.findAllByUserId(userId).contains(expectedSeat));
    }

    @Test
    public void createAllTest() {
        long auditoriumId = 10L;
        long showtimeId = 10L;

        Seat expectedSeat = Seat.builder()
                .name("createdTest")
                .availability(true)
                .build();
        seatRepository.createAll(List.of(expectedSeat), auditoriumId, showtimeId);
        seatRepository.createAll(List.of(expectedSeat), auditoriumId);
        expectedSeat.setId(1L);
        assertEquals(expectedSeat, jdbcTemplate.queryForObject("SELECT * FROM seats WHERE id = 1", new SeatMapper()));
        expectedSeat.setId(2L);
        assertEquals(expectedSeat, jdbcTemplate.queryForObject("SELECT * FROM seats WHERE id = 2", new SeatMapper()));

    }

    @Test
    public void reserveSeat() {
        long userId = 10L;
        long reserveId = 12L;
        long notExistedId = 11L;

        seatRepository.reserveSeat(reserveId, userId);
        assertNotEquals(Boolean.TRUE, jdbcTemplate.queryForObject("SELECT availability FROM seats WHERE id = ?", Boolean.class, reserveId));

        assertThrows(ReservationException.class, () -> seatRepository.reserveSeat(reserveId, userId));

        assertThrows(NotFoundException.class, () -> seatRepository.reserveSeat(notExistedId, userId));

    }

}
