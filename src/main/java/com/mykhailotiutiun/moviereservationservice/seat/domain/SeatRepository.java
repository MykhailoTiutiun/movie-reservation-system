package com.mykhailotiutiun.moviereservationservice.seat.domain;

import java.util.List;

public interface SeatRepository {

    //Select seat from diagram
    List<Seat> findAllByShowtimeId(Long showtimeId);

    List<Seat> findAllByAuditoriumId(Long auditoriumId);

    List<Seat> findAllByUserId(Long userId);

    // Add/Remove auditorium to showtime
    void createAllToShowtime(List<Seat> seats, Long showtimeId);

    void createAllToAuditorium(List<Seat> seats, Long auditoriumId);

    // Reserve free seat from list
    void reserveSeats(List<Long> ids, Long userId);
}
