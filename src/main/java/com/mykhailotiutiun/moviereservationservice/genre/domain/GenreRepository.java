package com.mykhailotiutiun.moviereservationservice.genre.domain;

import java.util.List;

public interface GenreRepository {

    List<Genre> findAll();

    Genre create(Genre genre);
    Genre update(Genre genre);
    void deleteById(Long id);
}
