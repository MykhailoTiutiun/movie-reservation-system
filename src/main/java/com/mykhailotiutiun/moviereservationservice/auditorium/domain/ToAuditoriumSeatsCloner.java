package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

public interface ToAuditoriumSeatsCloner {

    void cloneFromAuditoriumToAuditorium(Long fromAuditoriumId, Long toAuditoriumId);
}
