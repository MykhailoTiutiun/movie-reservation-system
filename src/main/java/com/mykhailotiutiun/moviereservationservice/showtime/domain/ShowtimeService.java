package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.util.List;

public interface ShowtimeService {

    //Select showtime from list
    List<Showtime> getListByAuditoriumId(Long auditoriumId);

    //Crud showtimes
    Showtime create(Showtime showtime, Long auditoriumId);

    Showtime update(Showtime showtime);

    void deleteById(Long id);

}
