package com.mykhailotiutiun.moviereservationservice.seat.domain;

import java.util.List;

public interface SeatService {

    //Select seat from diagram
    List<Seat> getListByShowtimeId(Long showtimeId);
    // Add/Remove auditorium to showtime
    void cloneSeatsToShowtime(Long auditoryId, Long showtimeId);
    // Reserve free seat from list
    void reserveSeat(Long id);
}
