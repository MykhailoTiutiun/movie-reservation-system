package com.mykhailotiutiun.moviereservationservice.seat.datasource;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.exception.ReservationException;
import com.mykhailotiutiun.moviereservationservice.seat.domain.Seat;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.util.List;

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
    public void createAll(List<Seat> seats, Long auditoriumId){
        jdbcTemplate.batchUpdate("INSERT INTO seats (name, availability, auditorium_id) VALUES (?, ?, ?)",
                seats,
                seats.size(),
                (PreparedStatement ps, Seat seat) -> {
                    ps.setString(1, seat.getName());
                    ps.setBoolean(2, seat.getAvailability());
                    ps.setLong(3, auditoriumId);
                }
        );
    }

    @Override
    public void createAll(List<Seat> seats, Long auditoriumId, Long showtimeId) {
        jdbcTemplate.batchUpdate("INSERT INTO seats (name, availability, auditorium_id, showtime_id) VALUES (?, ?, ?, ?)",
                seats,
                seats.size(),
                (PreparedStatement ps, Seat seat) -> {
            ps.setString(1, seat.getName());
            ps.setBoolean(2, seat.getAvailability());
            ps.setLong(3, auditoriumId);
            ps.setLong(4, showtimeId);
                }
        );
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
