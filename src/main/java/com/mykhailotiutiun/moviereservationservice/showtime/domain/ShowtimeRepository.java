package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.util.List;

public interface ShowtimeRepository {

    List<Showtime> findAllByAuditoriumId(Long auditoriumId);

    Showtime create(Showtime showtime, Long auditoriumId);

    Showtime update(Showtime showtime);

    void deleteById(Long id);
}
