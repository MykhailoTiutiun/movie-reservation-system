package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import java.util.List;

public interface AuditoriumService {
    Auditorium getById(Long id);

    //Select Auditorium from list
    List<Auditorium> getListByShowtimeId(Long showtimeId);
}
