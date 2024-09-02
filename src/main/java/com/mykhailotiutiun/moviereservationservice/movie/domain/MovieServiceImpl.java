package com.mykhailotiutiun.moviereservationservice.movie.domain;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getList() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.create(movie);
    }

    @Override
    public Movie update(Movie movie) {
        return movieRepository.update(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
