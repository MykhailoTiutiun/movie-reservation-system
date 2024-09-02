package com.mykhailotiutiun.moviereservationservice.cloner;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.ToAuditoriumSeatsCloner;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatService;

public class ToAuditoriumSeatsClonerImpl implements ToAuditoriumSeatsCloner {
    private final SeatService seatService;

    public ToAuditoriumSeatsClonerImpl(SeatService seatService) {
        this.seatService = seatService;
    }
    @Override
    public void cloneFromAuditoriumToAuditorium(Long fromAuditoriumId, Long toAuditoriumId) {
        seatService.cloneFromAuditoriumToAuditorium(fromAuditoriumId, toAuditoriumId);
    }
}
