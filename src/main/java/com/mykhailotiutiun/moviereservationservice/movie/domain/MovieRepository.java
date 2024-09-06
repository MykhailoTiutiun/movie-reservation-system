package com.mykhailotiutiun.moviereservationservice.movie.domain;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    List<Movie> findAll();

    Optional<Movie> findById(Long id);

    //Crud movies
    Movie create(Movie movie);

    Movie update(Movie movie);

    void addGenre(Long movieId, Long genreId);
    void removeGenre(Long movieId, Long genreId);

    void deleteById(Long id);
}
