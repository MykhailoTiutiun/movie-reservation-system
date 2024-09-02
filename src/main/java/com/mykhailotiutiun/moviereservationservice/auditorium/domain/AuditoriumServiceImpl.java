package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import com.mykhailotiutiun.moviereservationservice.exception.InvalidDeletionException;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;

import java.util.List;

public class AuditoriumServiceImpl implements AuditoriumService {

    private final AuditoriumRepository auditoriumRepository;
    private final ToAuditoriumSeatsCloner toAuditoriumSeatsCloner;

    public AuditoriumServiceImpl(AuditoriumRepository auditoriumRepository, ToAuditoriumSeatsCloner toAuditoriumSeatsCloner) {
        this.auditoriumRepository = auditoriumRepository;
        this.toAuditoriumSeatsCloner = toAuditoriumSeatsCloner;
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
    public Auditorium cloneToMovie(Long id, Long movieId) {
        Auditorium auditorium = getById(id);
        Auditorium linkedAuditorium = Auditorium.builder()
                .name(auditorium.getName())
                .description(auditorium.getDescription())
                .build();
        auditoriumRepository.create(linkedAuditorium, movieId);
        toAuditoriumSeatsCloner.cloneFromAuditoriumToAuditorium(auditorium.getId(), linkedAuditorium.getId());
        return linkedAuditorium;
    }

    @Override
    public void deleteById(Long id) {
        if(id <= 3){
            throw new InvalidDeletionException();
        }
        auditoriumRepository.deleteById(id);
    }
}
