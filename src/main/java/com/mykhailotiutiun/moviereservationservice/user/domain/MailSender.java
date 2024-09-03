package com.mykhailotiutiun.moviereservationservice.user.domain;

public interface MailSender {

    void sendUserVerificationToken(String token, String email);
}
