package com.mykhailotiutiun.moviereservationservice.user.domain;

import java.util.Optional;

public interface UserRepository {

    //Log in
    Optional<User> findByEmail(String username);

    //Registration
    User create(User user);
}
