package com.mykhailotiutiun.moviereservationservice.movie.datasource;

import com.mykhailotiutiun.moviereservationservice.movie.domain.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = Movie.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .imageId(rs.getLong("image_id"))
                .build();
        if(movie.getImageId() == 0){
            movie.setImageId(null);
        }
        return movie;
    }
}
