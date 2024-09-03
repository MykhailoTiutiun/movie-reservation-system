package com.mykhailotiutiun.moviereservationservice.user.datasource;

import com.mykhailotiutiun.moviereservationservice.user.domain.VerificationTokenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

    private final JdbcTemplate jdbcTemplate;

    public VerificationTokenRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Long> getUserIdByToken(String token) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT user_id FROM user_verification_tokens WHERE token = ?", Long.class, token));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void create(String token, Long userId) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("user_verification_tokens").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("user_id", userId);

        simpleJdbcInsert.execute(params);
    }
}
