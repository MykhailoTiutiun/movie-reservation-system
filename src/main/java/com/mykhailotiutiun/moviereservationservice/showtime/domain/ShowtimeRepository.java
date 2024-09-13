package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShowtimeRepository {

    List<Showtime> findAllByAuditoriumId(Long auditoriumId);
    List<Showtime> findAllByAuditoriumIdAndDate(Long auditoriumId, LocalDate date);
    Optional<Showtime> findById(Long id);

    Showtime create(Showtime showtime);

    Showtime update(Showtime showtime);

    void deleteById(Long id);
}
