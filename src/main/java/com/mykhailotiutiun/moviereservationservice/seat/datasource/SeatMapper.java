package com.mykhailotiutiun.moviereservationservice.seat.datasource;

import com.mykhailotiutiun.moviereservationservice.seat.domain.Seat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatMapper implements RowMapper<Seat> {
    @Override
    public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Seat.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .availability(rs.getBoolean("availability"))
                .build();
    }
}
