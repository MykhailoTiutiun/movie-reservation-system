package com.mykhailotiutiun.moviereservationservice.showtime.domain;

public interface ToShowtimeSeatsCloner {

    void cloneFromAuditoriumToShowtime(Long auditoriumId, Long showtimeId);
}
