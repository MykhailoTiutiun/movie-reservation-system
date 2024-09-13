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
    public List<Auditorium> findAll() {
        return jdbcTemplate.query("SELECT * FROM auditoriums", new AuditoriumMapper());
    }
}
