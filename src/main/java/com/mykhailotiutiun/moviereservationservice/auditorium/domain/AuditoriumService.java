package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import java.util.List;

public interface AuditoriumService {
    Auditorium getById(Long id);

    //Select Auditorium from list
    List<Auditorium> getListByMovieId(Long movieId);

    //Add/Remove to movie
    Auditorium cloneToMovie(Long id, Long movieId);
    void deleteById(Long id);
}
