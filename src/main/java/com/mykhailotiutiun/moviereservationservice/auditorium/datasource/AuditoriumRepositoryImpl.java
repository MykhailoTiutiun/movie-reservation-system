package com.mykhailotiutiun.moviereservationservice.auditorium.datasource;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumRepository;
import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuditoriumRepositoryImpl implements AuditoriumRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuditoriumRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Auditorium> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM auditoriums WHERE id = ?", new AuditoriumMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Auditorium> findAllByMovieId(Long movie_id) {
        if (movie_id == null) {
            return jdbcTemplate.query("SELECT * FROM auditoriums WHERE movie_id IS NULL", new AuditoriumMapper());
        }
        return jdbcTemplate.query("SELECT * FROM auditoriums WHERE movie_id = ?", new AuditoriumMapper(), movie_id);
    }

    @Override
    public Auditorium create(Auditorium auditorium) {
        try {
            jdbcTemplate.queryForObject("SELECT (1) FROM auditoriums WHERE name = ? AND description = ? AND movie_id = ?", Boolean.class,
                    auditorium.getName(), auditorium.getDescription(), auditorium.getMovieId());
            throw new AlreadyExistsException();
        } catch (EmptyResultDataAccessException ignored) {
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new AlreadyExistsException();
        }

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("auditoriums").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("name", auditorium.getName());
        params.put("description", auditorium.getDescription());
        params.put("movie_id", auditorium.getMovieId());

        Long id = (Long) simpleJdbcInsert.executeAndReturnKey(params);
        auditorium.setId(id);
        return auditorium;
    }

    @Override
    public void deleteById(Long id) {
        int result = jdbcTemplate.update("DELETE FROM auditoriums WHERE id = ?", id);
        if(result == 0){
            throw new NotFoundException();
        }
    }
}
