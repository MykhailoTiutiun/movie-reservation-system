package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import java.util.List;
import java.util.Optional;

public interface AuditoriumRepository {

    Optional<Auditorium> findById(Long id);

    List<Auditorium> findAll();

}
