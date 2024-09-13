package com.mykhailotiutiun.moviereservationservice.seat.domain;

import java.util.List;

public interface SeatService {

    //Select seat from diagram
    List<Seat> getListByShowtimeId(Long showtimeId);

    //View reserve seats
    List<Seat> getListByUserId(Long userId);

    // Add/Remove auditorium to movie
    void cloneFromAuditoriumToAuditorium(Long fromAuditoriumId, Long toAuditoriumId);
    // Add/Remove showtime to auditorium
    void cloneFromAuditoriumToShowtime(Long auditoriumId, Long showtimeId);

    // Reserve free seat from list
    void reserveSeats(List<Long> ids, Long userId);
}
