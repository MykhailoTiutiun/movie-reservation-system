package com.mykhailotiutiun.moviereservationservice.auditorium.datasource;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumRepository;
import com.mykhailotiutiun.moviereservationservice.exceptions.AlreadyExistsException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return jdbcTemplate.query("SELECT * FROM auditoriums WHERE movie_id = ?", new AuditoriumMapper(), movie_id);
    }

    @Override
    public Auditorium create(Auditorium auditorium, Long movieId) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("auditoriums").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("name", auditorium.getName());
        params.put("description", auditorium.getDescription());
        params.put("movie_id", movieId);

        try {
            Long id = (Long) simpleJdbcInsert.executeAndReturnKey(params);
            auditorium.setId(id);
            return auditorium;
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException();
        }
    }
}
