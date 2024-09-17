package com.mykhailotiutiun.moviereservationservice.cloner;

import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatService;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ToShowtimeSeatsCloner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClonerConfig {

    @Bean
    public ToShowtimeSeatsCloner toShowtimeSeatsCloner(SeatService seatService){
        return new ToShowtimeSeatsClonerImpl(seatService);
    }
}
