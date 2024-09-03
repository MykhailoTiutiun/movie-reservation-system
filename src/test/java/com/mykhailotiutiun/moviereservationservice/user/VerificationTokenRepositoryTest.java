package com.mykhailotiutiun.moviereservationservice.user;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.user.datasource.VerificationTokenRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerificationTokenRepositoryTest {

    private final VerificationTokenRepositoryImpl verificationTokenRepository;
    private final JdbcTemplate jdbcTemplate;

    public VerificationTokenRepositoryTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:database/schema.sql")
                .addScript("classpath:database/test_data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(dataSource);
        verificationTokenRepository = new VerificationTokenRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void getUserIdByTokenTest(){
        Long expectedUserId = 10L;
        String token = "Test";
        String notExistedToken = "NotExistedTest";

        assertEquals(expectedUserId, verificationTokenRepository.getUserIdByToken(token).orElseThrow(NotFoundException::new));
        assertTrue(verificationTokenRepository.getUserIdByToken(notExistedToken).isEmpty());
    }

    @Test
    public void create(){
        String token = "CreatedTest";
        Long userId = 10L;

        verificationTokenRepository.create(token, userId);
        assertTrue(jdbcTemplate.queryForList("SELECT token FROM user_verification_tokens WHERE user_id = ?", String.class, userId).contains(token));
    }
}
