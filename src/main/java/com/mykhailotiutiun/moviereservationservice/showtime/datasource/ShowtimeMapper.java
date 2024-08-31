package com.mykhailotiutiun.moviereservationservice.showtime.datasource;

import com.mykhailotiutiun.moviereservationservice.showtime.domain.Showtime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowtimeMapper implements RowMapper<Showtime> {
    @Override
    public Showtime mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Showtime.builder()
                .id(rs.getLong("id"))
                .date(rs.getDate("date").toLocalDate())
                .startTime(rs.getTime("start_time").toLocalTime())
                .endTime(rs.getTime("end_time").toLocalTime())
                .build();
    }
}
