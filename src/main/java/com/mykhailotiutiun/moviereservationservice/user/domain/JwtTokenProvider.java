package com.mykhailotiutiun.moviereservationservice.user.domain;

public interface JwtTokenProvider {

    String generateToken(User user);
}
