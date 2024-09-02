package com.mykhailotiutiun.moviereservationservice.seat.domain;

import java.util.List;

public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getListByShowtimeId(Long showtimeId) {
        return seatRepository.findAllByShowtimeId(showtimeId);
    }

    @Override
    public List<Seat> getListByUserId(Long userId) {
        return seatRepository.findAllByUserId(userId);
    }

    @Override
    public void cloneFromAuditoriumToAuditorium(Long fromAuditoriumId, Long toAuditoriumId) {
        List<Seat> seats = seatRepository.findAllByAuditoriumId(fromAuditoriumId);
        seats.forEach(seat -> seatRepository.create(Seat.builder()
                .name(seat.getName())
                .availability(false).build(), toAuditoriumId, null));
    }

    @Override
    public void cloneFromAuditoriumToShowtime(Long auditoriumId, Long showtimeId) {
        List<Seat> seats = seatRepository.findAllByAuditoriumId(auditoriumId);
        seats.forEach(seat -> seatRepository.create(Seat.builder()
                .name(seat.getName())
                .availability(true).build(), auditoriumId, showtimeId));
    }

    @Override
    public void reserveSeat(Long id, Long userId) {
        seatRepository.reserveSeat(id, userId);
    }
}
