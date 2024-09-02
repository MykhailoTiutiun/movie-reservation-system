package com.mykhailotiutiun.moviereservationservice.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class DatabaseInit {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseInit(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @PostConstruct
    private void initSeats() {
        int seatId = 1;
        if(Objects.equals(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM seats WHERE auditorium_id BETWEEN 1 and 3", Integer.class), 692)){
            return;
        }

        // Imax
        // Rows A-F (36 seats each)
        String[] rows36 = {"A", "B", "C", "D", "E", "F"};
        for (String row : rows36) {
            for (int seatNum = 1; seatNum <= 36; seatNum++) {
                insertSeat(seatId, row + seatNum, 1);
                seatId++;
            }
        }
        // Rows G-L (29 seats each)
        String[] rows29 = {"G", "H", "I", "J", "K", "L"};
        for (String row : rows29) {
            for (int seatNum = 1; seatNum <= 29; seatNum++) {
                insertSeat(seatId,  row + seatNum, 1);
                seatId++;
            }
        }
        // Row M (38 seats)
        String rowM = "M";
        for (int seatNum = 1; seatNum <= 38; seatNum++) {
            insertSeat(seatId, rowM + seatNum, 1);
            seatId++;
        }

        // 4DX
        // Rows A-C (12 seats each)
        String[] rows12 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (String row : rows12) {
            for (int seatNum = 1; seatNum <= 12; seatNum++) {
                insertSeat(seatId,  row + seatNum, 2);
                seatId++;
            }
        }

        // 3D
        // Rows A-I (16 seats each)
        String[] rows16 = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        for (String row : rows16) {
            for (int seatNum = 1; seatNum <= 16; seatNum++) {
                insertSeat(seatId,  row + seatNum, 3);
                seatId++;
            }
        }

        jdbcTemplate.execute("SELECT setval('seats_id_seq', (SELECT COALESCE(MAX(id) + 1, 1) FROM seats), false)");
    }

    private void insertSeat(int seatId, String seatName, int auditoriumId) {
        jdbcTemplate.update("INSERT INTO seats(id, name, availability, auditorium_id, showtime_id, user_id) " +
                "SELECT ?, ?, false, ?, NULL, NULL " +
                "WHERE NOT EXISTS (SELECT 1 FROM seats WHERE id = ?);", seatId, seatName, auditoriumId, seatId);
    }

}
