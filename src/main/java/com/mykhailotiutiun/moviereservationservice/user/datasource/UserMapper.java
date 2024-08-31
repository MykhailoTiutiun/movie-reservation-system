package com.mykhailotiutiun.moviereservationservice.user.datasource;

import com.mykhailotiutiun.moviereservationservice.user.domain.User;
import com.mykhailotiutiun.moviereservationservice.user.domain.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .role(UserRole.valueOf(rs.getString("role")))
                .build();
    }
}
