package com.mykhailotiutiun.moviereservationservice.seat.domain;

import java.util.Comparator;
import java.util.List;

public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getListByShowtimeId(Long showtimeId) {
        List<Seat> seats = seatRepository.findAllByShowtimeId(showtimeId);
        if(seats.size() > 1) {
            seats.sort(Comparator.comparing(Seat::getId));
        }
        return seats;
    }

    @Override
    public List<Seat> getListByUserId(Long userId) {
        return seatRepository.findAllByUserId(userId);
    }

    @Override
    public void cloneFromAuditoriumToAuditorium(Long fromAuditoriumId, Long toAuditoriumId) {
        List<Seat> seats = seatRepository.findAllByAuditoriumId(fromAuditoriumId);
        List<Seat> newSeats = seats.stream().map(seat -> Seat.builder()
                .name(seat.getName())
                .availability(false).build()).toList();

        seatRepository.createAllToAuditorium(newSeats, toAuditoriumId);
    }

    @Override
    public void cloneFromAuditoriumToShowtime(Long auditoriumId, Long showtimeId) {
        List<Seat> seats = seatRepository.findAllByAuditoriumId(auditoriumId);
        List<Seat> newSeats = seats.stream().map(seat -> Seat.builder()
                .name(seat.getName())
                .availability(true).build()).toList();
        seatRepository.createAllToShowtime(newSeats, showtimeId);
    }

    @Override
    public void reserveSeats(List<Long> ids, Long userId) {
        seatRepository.reserveSeats(ids, userId);
    }
}
