package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.util.List;

public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @Override
    public List<Showtime> getListByAuditoriumId(Long auditoriumId) {
        return showtimeRepository.findAllByAuditoriumId(auditoriumId);
    }

    @Override
    public Showtime create(Showtime showtime, Long auditoriumId) {
        return showtimeRepository.create(showtime, auditoriumId);
    }

    @Override
    public Showtime update(Showtime showtime) {
        return showtimeRepository.update(showtime);
    }

    @Override
    public void deleteById(Long id) {
        showtimeRepository.deleteById(id);
    }
}
