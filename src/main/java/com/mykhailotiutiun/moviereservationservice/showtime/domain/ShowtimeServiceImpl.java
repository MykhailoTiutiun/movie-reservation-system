package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Comparator;
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
    public List<Showtime> getListByAuditoriumId(Long auditoriumId, LocalDate date) {
        List<Showtime> showtimes = showtimeRepository.findAllByAuditoriumIdAndDate(auditoriumId, date);
        if(showtimes.size() > 1) {
            showtimes.sort(Comparator.comparing(Showtime::getStartTime));
        }
        return showtimes;
    }

    @Override
    public List<Showtime> getListByMovieId(Long movieId) {
        return showtimeRepository.findAllByMovieId(movieId);
    }

    @Override
    public List<Showtime> getListByMovieId(Long movieId, LocalDate date) {
        List<Showtime> showtimes = showtimeRepository.findAllByMovieIdAndDate(movieId, date);
        if(showtimes.size() > 1) {
            showtimes.sort(Comparator.comparing(Showtime::getStartTime));
        }
        return showtimes;
    }

    @Override
    public List<Showtime> getListByDate(LocalDate date) {
        return showtimeRepository.findAllByDate(date);
    }

    @Override
    public Showtime getById(Long id) {
        return showtimeRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Showtime create(Showtime showtime) {
        showtimeRepository.create(showtime);
        showtimeSeatsCloner.cloneFromAuditoriumToShowtime(showtime.getAuditoriumId(), showtime.getId());
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
