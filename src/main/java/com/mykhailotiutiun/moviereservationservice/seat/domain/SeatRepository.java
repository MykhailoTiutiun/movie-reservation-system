package com.mykhailotiutiun.moviereservationservice.seat.domain;

import java.util.List;

public interface SeatRepository {

    //Select seat from diagram
    List<Seat> findAllByShowtimeId(Long showtimeId);

    List<Seat> findAllByAuditoriumId(Long auditoriumId);

    List<Seat> findAllByUserId(Long userId);

    // Add/Remove auditorium to showtime
    void createAll(List<Seat> seats, Long auditoriumId);

    void createAll(List<Seat> seats, Long auditoriumId, Long showtimeId);

    // Reserve free seat from list
    void reserveSeat(Long id, Long userId);
}
