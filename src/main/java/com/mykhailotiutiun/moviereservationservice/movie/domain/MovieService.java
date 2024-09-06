package com.mykhailotiutiun.moviereservationservice.movie.domain;

import java.util.List;

public interface MovieService {

    //Select movie from list
    List<Movie> getList();

    Movie getById(Long id);

    //Crud movies
    Movie create(Movie movie);
    Movie update(Movie movie);

    void addGenre(Long movieId, Long genreId);
    void removeGenre(Long movieId, Long genreId);

    void deleteById(Long id);

}
