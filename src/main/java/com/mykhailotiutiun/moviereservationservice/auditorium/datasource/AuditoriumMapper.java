package com.mykhailotiutiun.moviereservationservice.auditorium.datasource;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditoriumMapper implements RowMapper<Auditorium> {
    @Override
    public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auditorium auditorium = Auditorium.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .movieId(rs.getLong("movie_id"))
                .build();
        if(auditorium.getMovieId() == 0){
            auditorium.setMovieId(null);
        }
        return auditorium;
    }
}
