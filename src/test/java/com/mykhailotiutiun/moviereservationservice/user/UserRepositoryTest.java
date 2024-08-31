package com.mykhailotiutiun.moviereservationservice.user;

import com.mykhailotiutiun.moviereservationservice.exceptions.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exceptions.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.user.datasource.UserMapper;
import com.mykhailotiutiun.moviereservationservice.user.datasource.UserRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.user.domain.User;
import com.mykhailotiutiun.moviereservationservice.user.domain.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private final UserRepositoryImpl userRepository;
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:database/schema.sql")
                .addScript("classpath:database/test_data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(dataSource);
        userRepository = new UserRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void findByUsername() {
        String existedUsername = "Test";
        String notExistedUsername = "NotExistedTest";
        User expectedUser = User.builder()
                .id(10L)
                .username("Test")
                .password("Test")
                .role(UserRole.USER)
                .build();
        assertEquals(expectedUser, userRepository.findByUsername(existedUsername).orElseThrow(NotFoundException::new));
        assertTrue(userRepository.findByUsername(notExistedUsername).isEmpty());
    }

    @Test
    public void createTest() {
        User expectedUser = User.builder()
                .username("createdTest")
                .password("createdTest")
                .role(UserRole.USER)
                .build();
        userRepository.create(expectedUser);
        assertEquals(expectedUser, jdbcTemplate.queryForObject("SELECT * FROM app_users WHERE id = ?", new UserMapper(), expectedUser.getId()));

        assertThrows(AlreadyExistsException.class, () -> userRepository.create(expectedUser));
    }

}
