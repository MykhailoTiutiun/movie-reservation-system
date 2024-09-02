package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.util.List;

public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final ToShowtimeSeatsCloner showtimeSeatsCloner;

    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository, ToShowtimeSeatsCloner showtimeSeatsCloner) {
        this.showtimeRepository = showtimeRepository;
        this.showtimeSeatsCloner = showtimeSeatsCloner;
    }

    @Override
    public List<Showtime> getListByAuditoriumId(Long auditoriumId) {
        return showtimeRepository.findAllByAuditoriumId(auditoriumId);
    }

    @Override
    public Showtime create(Showtime showtime, Long auditoriumId) {
        showtimeRepository.create(showtime, auditoriumId);
        showtimeSeatsCloner.cloneFromAuditoriumToShowtime(auditoriumId, showtime.getId());
        return showtime;
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
