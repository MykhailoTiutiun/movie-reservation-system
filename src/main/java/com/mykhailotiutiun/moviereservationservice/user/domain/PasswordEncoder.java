package com.mykhailotiutiun.moviereservationservice.user.domain;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
