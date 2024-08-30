package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.util.List;

public interface ShowtimeRepository {

    List<Showtime> findAllByMovieId(Long movieId);

    Showtime create(Showtime showtime, Long movieId);
    Showtime update(Showtime showtime);
    void deleteById(Long id);
}
