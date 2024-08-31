package com.mykhailotiutiun.moviereservationservice.user.domain;

import java.util.Optional;

public interface UserRepository {

    //Log in
    Optional<User> findByUsername(String username);

    //Registration
    User create(User user);
}
