package com.mykhailotiutiun.moviereservationservice.movie.datasource;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
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
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM movies", new MovieMapper());
        movies.forEach(movie -> {
            movie.setGenres(jdbcTemplate.query("SELECT id, name FROM genres INNER JOIN movies_genres ON id = genre_id WHERE movie_id = ?", rs -> {
                Map<Long, String> map = new HashMap<>();
                while(rs.next()){
                    map.put(rs.getLong("id"), rs.getString("name"));
                }
                return map;
            }, movie.getId()));
        });
        return movies;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        try {
            Movie movie = jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", new MovieMapper(), id);
            //noinspection ConstantConditions
            movie.setGenres(jdbcTemplate.query("SELECT id, name FROM genres INNER JOIN movies_genres ON id = genre_id WHERE movie_id = ?", rs -> {
                Map<Long, String> map = new HashMap<>();
                while(rs.next()){
                    map.put(rs.getLong("id"), rs.getString("name"));
                }
                return map;
            }, id));
            return Optional.of(movie);
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
        int result = jdbcTemplate.update("UPDATE movies SET title = ?, description = ? WHERE id = ?", movie.getTitle(), movie.getDescription(), movie.getId());
        if(result == 0){
            throw new NotFoundException();
        }
        return movie;
    }

    @Override
    public void addGenre(Long movieId, Long genreId) {
        jdbcTemplate.update("INSERT INTO movies_genres(movie_id, genre_id) VALUES (?, ?)", movieId, genreId);
    }

    @Override
    public void removeGenre(Long movieId, Long genreId) {
        jdbcTemplate.update("DELETE FROM movies_genres WHERE movie_id = ? AND genre_id = ?", movieId, genreId);
    }

    @Override
    public void deleteById(Long id) {
        int result = jdbcTemplate.update("DELETE FROM movies WHERE id = ?", id);
        if(result == 0){
            throw new NotFoundException();
        }
    }
}
