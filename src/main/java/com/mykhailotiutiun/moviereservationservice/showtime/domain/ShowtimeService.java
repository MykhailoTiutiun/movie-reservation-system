package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.util.List;

public interface ShowtimeService {

    //Select showtime from list
    List<Showtime> getListByMovieId(Long movieId);

    //Crud showtimes
    Showtime create(Showtime showtime, Long movieId);
    Showtime update(Showtime showtime);
    void deleteById(Long id);

}
