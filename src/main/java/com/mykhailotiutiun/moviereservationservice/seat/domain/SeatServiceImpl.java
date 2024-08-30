package com.mykhailotiutiun.moviereservationservice.seat.domain;

import com.mykhailotiutiun.moviereservationservice.exceptions.NotFoundException;

import java.util.List;

public class SeatServiceImpl implements SeatService{

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getListByShowtimeId(Long showtimeId) {
        return seatRepository.findAllByShowtimeId(showtimeId);
    }

    @Override
    public void cloneSeatsToShowtime(Long auditoryId, Long showtimeId) {
        List<Seat> seats = seatRepository.findAllByAuditoriumId(auditoryId);
        seats.forEach(seat -> seatRepository.create(Seat.builder()
                .name(seat.getName())
                .availability(true).build(), showtimeId));
    }

    @Override
    public void reserveSeat(Long id) {
        seatRepository.reserveSeat(id);
    }
}
