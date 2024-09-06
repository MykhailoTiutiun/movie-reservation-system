package com.mykhailotiutiun.moviereservationservice.genres.domain;

import java.util.List;

public interface GenreService {

    List<Genre> getList();

    Genre create(Genre genre);
    Genre update(Genre genre);
    void deleteById(Long id);
}
