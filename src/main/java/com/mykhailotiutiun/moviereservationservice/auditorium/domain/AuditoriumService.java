package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import java.util.List;

public interface AuditoriumService {
    Auditorium getById(Long id);

    List<Auditorium> getList();
}
