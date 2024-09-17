package com.mykhailotiutiun.moviereservationservice.genre.domain;

import java.util.Comparator;
import java.util.List;

public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getList() {
        List<Genre> genres = genreRepository.findAll();
        if(genres.size() > 1){
            genres.sort(Comparator.comparing(Genre::getName));
        }
        return genres;
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
