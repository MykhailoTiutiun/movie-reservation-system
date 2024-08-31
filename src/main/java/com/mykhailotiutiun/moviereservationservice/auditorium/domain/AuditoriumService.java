package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import java.util.List;

public interface AuditoriumService {
    Auditorium getById(Long id);

    //Select Auditorium from list
    List<Auditorium> getListByMovieId(Long movieId);

    //Crud showtimes
    void copyToMovie(Long id, Long movieId);
}
