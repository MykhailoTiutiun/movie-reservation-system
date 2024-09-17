package com.mykhailotiutiun.moviereservationservice.showtime.datasource;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.Showtime;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShowtimeRepositoryImpl implements ShowtimeRepository {

    private final JdbcTemplate jdbcTemplate;

    public ShowtimeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Showtime> findAllByAuditoriumId(Long auditoriumId) {
        return jdbcTemplate.query("SELECT * FROM showtimes WHERE auditorium_id = ?", new ShowtimeMapper(), auditoriumId);
    }

    @Override
    public List<Showtime> findAllByAuditoriumIdAndDate(Long auditoriumId, LocalDate date) {
        return jdbcTemplate.query("SELECT * FROM showtimes WHERE auditorium_id = ? AND date = ?", new ShowtimeMapper(), auditoriumId, date);
    }

    @Override
    public List<Showtime> findAllByMovieId(Long movieId) {
        return jdbcTemplate.query("SELECT * FROM showtimes WHERE movie_id = ?", new ShowtimeMapper(), movieId);
    }

    @Override
    public List<Showtime> findAllByMovieIdAndDate(Long movieId, LocalDate date) {
        return jdbcTemplate.query("SELECT * FROM showtimes WHERE movie_id = ? AND date = ?", new ShowtimeMapper(), movieId, date);
    }

    @Override
    public List<Showtime> findAllByDate(LocalDate date) {
        return jdbcTemplate.query("SELECT * FROM showtimes WHERE date = ?", new ShowtimeMapper(), date);
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM showtimes WHERE id = ?", new ShowtimeMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Showtime create(Showtime showtime) {
        try {
            jdbcTemplate.queryForObject("SELECT (1) FROM showtimes WHERE date = ? AND auditorium_id = ? AND (end_time > ? AND start_time < ?)", Boolean.class,
                    showtime.getDate(), showtime.getAuditoriumId(), showtime.getStartTime(), showtime.getEndTime());
            throw new AlreadyExistsException();
        } catch (EmptyResultDataAccessException ignored) {
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new AlreadyExistsException();
        }

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("showtimes").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("date", showtime.getDate());
        params.put("start_time", showtime.getStartTime());
        params.put("end_time", showtime.getEndTime());
        params.put("auditorium_id", showtime.getAuditoriumId());
        params.put("movie_id", showtime.getMovieId());
        Long id = (Long) simpleJdbcInsert.executeAndReturnKey(params);
        showtime.setId(id);
        return showtime;
    }

    @Override
    public Showtime update(Showtime showtime) {
        try {
            jdbcTemplate.queryForObject("SELECT (1) FROM showtimes WHERE date = ? AND auditorium_id = (SELECT auditorium_id from showtimes WHERE id = ?) AND (end_time > ? AND start_time < ?) AND id != ?", Boolean.class,
                    showtime.getDate(), showtime.getId(), showtime.getStartTime(), showtime.getEndTime(), showtime.getId());
            throw new AlreadyExistsException();
        } catch (EmptyResultDataAccessException ignored) {
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new AlreadyExistsException();
        }

        int result = jdbcTemplate.update("UPDATE showtimes SET date = ?, start_time = ?, end_time = ? WHERE id = ?", showtime.getDate(), showtime.getStartTime(), showtime.getEndTime(), showtime.getId());
        if(result == 0){
            throw new NotFoundException();
        }

        return jdbcTemplate.queryForObject("SELECT * FROM showtimes WHERE id = ?", new ShowtimeMapper(), showtime.getId());
    }

    @Override
    public void deleteById(Long id) {
        int result = jdbcTemplate.update("DELETE FROM showtimes WHERE id = ?", id);
        if(result == 0){
            throw new NotFoundException();
        }
    }
}
