package com.mykhailotiutiun.moviereservationservice.user.domain;

import java.util.Optional;

public interface VerificationTokenRepository {

    Optional<Long> getUserIdByToken(String token);
    void create(String token, Long userId);
}
