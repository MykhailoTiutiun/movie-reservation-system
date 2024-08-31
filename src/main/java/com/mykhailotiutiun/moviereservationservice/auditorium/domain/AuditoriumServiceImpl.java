package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import com.mykhailotiutiun.moviereservationservice.exceptions.NotFoundException;

import java.util.List;

public class AuditoriumServiceImpl implements AuditoriumService {

    private final AuditoriumRepository auditoriumRepository;

    public AuditoriumServiceImpl(AuditoriumRepository auditoriumRepository) {
        this.auditoriumRepository = auditoriumRepository;
    }

    @Override
    public Auditorium getById(Long id) {
        return auditoriumRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Auditorium> getListByMovieId(Long movieId) {
        return auditoriumRepository.findAllByMovieId(movieId);
    }

    @Override
    public void copyToMovie(Long id, Long movieId) {
        Auditorium auditorium = getById(id);
        auditoriumRepository.create(Auditorium.builder()
                .name(auditorium.getName())
                .description(auditorium.getDescription())
                .build(), movieId);
    }
}
