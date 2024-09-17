package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeService {

    //Select showtime from list
    List<Showtime> getListByAuditoriumId(Long auditoriumId);
    List<Showtime> getListByAuditoriumId(Long auditoriumId, LocalDate date);
    List<Showtime> getListByMovieId(Long movieId);
    List<Showtime> getListByMovieId(Long movieId, LocalDate date);
    List<Showtime> getListByDate(LocalDate date);
    Showtime getById(Long id);

    //Crud showtimes
    Showtime create(Showtime showtime);

    Showtime update(Showtime showtime);

    void deleteById(Long id);

}
