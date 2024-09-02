package com.mykhailotiutiun.moviereservationservice.cloner;

import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatService;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ToShowtimeSeatsCloner;

public class ToShowtimeSeatsClonerImpl implements ToShowtimeSeatsCloner {

    private final SeatService seatService;

    public ToShowtimeSeatsClonerImpl(SeatService seatService) {
        this.seatService = seatService;
    }

    @Override
    public void cloneFromAuditoriumToShowtime(Long auditoriumId, Long showtimeId) {
        seatService.cloneFromAuditoriumToShowtime(auditoriumId, showtimeId);
    }
}
