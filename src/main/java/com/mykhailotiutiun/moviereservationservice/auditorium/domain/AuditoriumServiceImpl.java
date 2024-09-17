package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;

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
    public List<Auditorium> getList() {
        return auditoriumRepository.findAll();
    }

}
