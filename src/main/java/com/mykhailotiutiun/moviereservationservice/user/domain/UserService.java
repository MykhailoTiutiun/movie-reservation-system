package com.mykhailotiutiun.moviereservationservice.user.domain;

public interface UserService {

    //Log in
    String getToken(User user);

    //Registration
    User register(User user);
}
