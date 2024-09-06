package com.mykhailotiutiun.moviereservationservice.genres.domain;

import java.util.List;

public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getList() {
        return genreRepository.findAll();
    }

    @Override
    public Genre create(Genre genre) {
        return genreRepository.create(genre);
    }

    @Override
    public Genre update(Genre genre) {
        return genreRepository.update(genre);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
