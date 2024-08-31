package com.mykhailotiutiun.moviereservationservice.user.domain;

public interface UserService {

    //Log in
    User getByUsername(String username);

    //Registration
    User create(User user);
}
