package com.mykhailotiutiun.moviereservationservice.user.token;

import com.mykhailotiutiun.moviereservationservice.user.domain.VerificationTokenProvider;

import java.util.UUID;

public class VerificationTokenCreator implements VerificationTokenProvider {

    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
