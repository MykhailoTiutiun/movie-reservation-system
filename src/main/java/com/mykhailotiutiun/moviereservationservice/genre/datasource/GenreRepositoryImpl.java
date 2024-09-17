package com.mykhailotiutiun.moviereservationservice.genre.datasource;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.genre.domain.Genre;
import com.mykhailotiutiun.moviereservationservice.genre.domain.GenreRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreRepositoryImpl implements GenreRepository {

    private final JdbcTemplate jdbcTemplate;

    public GenreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query("SELECT * FROM genres", new GenreMapper());
    }

    @Override
    public Genre create(Genre genre) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("genres").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", genre.getName());

        try {
            Long id = (Long) simpleJdbcInsert.executeAndReturnKey(params);
            genre.setId(id);
            return genre;
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException();
        }
    }

    @Override
    public Genre update(Genre genre) {
        int result = jdbcTemplate.update("UPDATE genres SET name = ? WHERE id = ?", genre.getName(), genre.getId());
        if(result == 0){
            throw new NotFoundException();
        }
        return genre;
    }

    @Override
    public void deleteById(Long id) {
        int result = jdbcTemplate.update("DELETE FROM genres WHERE id = ?", id);
        if(result == 0){
            throw new NotFoundException();
        }
    }
}
