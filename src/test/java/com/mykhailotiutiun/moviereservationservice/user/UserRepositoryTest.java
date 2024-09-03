package com.mykhailotiutiun.moviereservationservice.user;

import com.mykhailotiutiun.moviereservationservice.exception.AlreadyExistsException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
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
        String existedEmail = "Test";
        String notExistedEmail = "NotExistedTest";
        User expectedUser = User.builder()
                .id(10L)
                .email("Test")
                .password("Test")
                .verified(false)
                .role(UserRole.USER)
                .build();
        assertEquals(expectedUser, userRepository.findByEmail(existedEmail).orElseThrow(NotFoundException::new));
        assertTrue(userRepository.findByEmail(notExistedEmail).isEmpty());
    }

    @Test
    public void createTest() {
        User expectedUser = User.builder()
                .email("createdTest")
                .password("createdTest")
                .verified(false)
                .role(UserRole.USER)
                .build();
        userRepository.create(expectedUser);
        assertEquals(expectedUser, jdbcTemplate.queryForObject("SELECT app_users.id as id, email, password, verified, user_roles.name as role FROM app_users LEFT JOIN user_roles on role_id = user_roles.id WHERE app_users.id = ?", new UserMapper(), expectedUser.getId()));

        assertThrows(AlreadyExistsException.class, () -> userRepository.create(expectedUser));
    }

    @Test
    public void verifyTest() {
        long notExistedId = 11L;
        User expectedUser = User.builder()
                .id(12L)
                .email("VerifyTest")
                .password("VerifyTest")
                .verified(true)
                .role(UserRole.USER)
                .build();
        userRepository.verifyUser(expectedUser.getId());
        assertEquals(expectedUser, jdbcTemplate.queryForObject("SELECT app_users.id as id, email, password, verified, user_roles.name as role FROM app_users LEFT JOIN user_roles on role_id = user_roles.id WHERE app_users.id = ?", new UserMapper(), expectedUser.getId()));

        assertThrows(NotFoundException.class, () -> userRepository.verifyUser(notExistedId));
    }

}
