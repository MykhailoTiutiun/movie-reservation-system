package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeService {

    //Select showtime from list
    List<Showtime> getList(Long auditoriumId);
    List<Showtime> getList(Long auditoriumId, LocalDate date);
    Showtime getById(Long id);

    //Crud showtimes
    Showtime create(Showtime showtime);

    Showtime update(Showtime showtime);

    void deleteById(Long id);

}
