package com.mykhailotiutiun.moviereservationservice.seat.datasource;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.exception.ReservationException;
import com.mykhailotiutiun.moviereservationservice.seat.domain.Seat;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatRepositoryImpl implements SeatRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public SeatRepositoryImpl(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public List<Seat> findAllByShowtimeId(Long showtimeId) {
        return jdbcTemplate.query("SELECT * FROM seats WHERE showtime_id = ?", new SeatMapper(), showtimeId);
    }

    @Override
    public List<Seat> findAllByAuditoriumId(Long auditoriumId) {
        return jdbcTemplate.query("SELECT * FROM seats WHERE auditorium_id = ?", new SeatMapper(), auditoriumId);
    }

    @Override
    public List<Seat> findAllByUserId(Long userId) {
        return jdbcTemplate.query("SELECT * FROM seats WHERE user_id = ?", new SeatMapper(), userId);
    }

    @Override
    public Seat create(Seat seat, Long auditoriumId, Long showtimeId) {
        try {
            jdbcTemplate.queryForObject("SELECT (1) FROM seats WHERE name = ? AND auditorium_id = ? AND showtime_id = ?", Boolean.class,
                    seat.getName(), auditoriumId, showtimeId);
            throw new AlreadyExistsException();
        } catch (EmptyResultDataAccessException ignored) {
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new AlreadyExistsException();
        }

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("seats").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("name", seat.getName());
        params.put("availability", seat.getAvailability());
        params.put("auditorium_id", auditoriumId);
        params.put("showtime_id", showtimeId);

        Long id = (Long) simpleJdbcInsert.executeAndReturnKey(params);
        seat.setId(id);
        return seat;
    }

    @Override
    public void reserveSeat(Long id, Long userId) {
        transactionTemplate.execute(status -> {
            try {
                if (!Boolean.TRUE.equals(jdbcTemplate.queryForObject("SELECT availability FROM seats WHERE id = ? FOR UPDATE ", Boolean.class, id))) {
                    throw new ReservationException();
                }
            } catch (EmptyResultDataAccessException e) {
                throw new NotFoundException();
            }

            jdbcTemplate.update("UPDATE seats SET availability = false, user_id = ? WHERE id = ?", userId, id);
            return null;
        });
    }
}
