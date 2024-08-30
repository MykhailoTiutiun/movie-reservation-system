package com.mykhailotiutiun.moviereservationservice.seat.domain;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {

    //Select seat from diagram
    List<Seat> findAllByShowtimeId(Long showtimeId);
    List<Seat> findAllByAuditoriumId(Long auditoriumId);
    // Add/Remove auditorium to showtime
    Seat create(Seat seat, Long showtimeId);
    // Reserve free seat from list
    void reserveSeat(Long id);
}
