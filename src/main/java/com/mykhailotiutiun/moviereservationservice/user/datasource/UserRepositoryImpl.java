package com.mykhailotiutiun.moviereservationservice.user.datasource;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.user.domain.User;
import com.mykhailotiutiun.moviereservationservice.user.domain.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByEmail(String username) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT app_users.id as id, email, password, verified, user_roles.name as role FROM app_users LEFT JOIN user_roles on role_id = user_roles.id WHERE email = ?", new UserMapper(), username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User create(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("app_users").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("verified", user.isVerified());
        try {
            Long id = (Long) simpleJdbcInsert.executeAndReturnKey(params);
            user.setId(id);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException();
        }
        jdbcTemplate.update("UPDATE app_users SET role_id = (SELECT id FROM user_roles WHERE name = ?) WHERE id = ?", user.getRole().name(), user.getId());

        return user;
    }

    @Override
    public void verifyUser(Long userId) {
        int result = jdbcTemplate.update("UPDATE app_users SET verified = true WHERE id = ?", userId);
        if (result == 0) {
            throw new NotFoundException();
        }
    }
}
