package com.mykhailotiutiun.moviereservationservice.movie.datasource;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.movie.domain.Movie;
import com.mykhailotiutiun.moviereservationservice.movie.domain.MovieRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MovieRepositoryImpl implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public MovieRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM movies", new MovieMapper());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", new MovieMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Movie create(Movie movie) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("movies").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>(2);
        params.put("title", movie.getTitle());
        params.put("description", movie.getDescription());

        try {
            Long id = (Long) simpleJdbcInsert.executeAndReturnKey(params);
            movie.setId(id);
            return movie;
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException();
        }
    }

    @Override
    public Movie update(Movie movie) {
        jdbcTemplate.update("UPDATE movies SET title = ?, description = ? WHERE id = ?", movie.getTitle(), movie.getDescription(), movie.getId());
        return movie;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM movies WHERE id = ?", id);
    }
}
