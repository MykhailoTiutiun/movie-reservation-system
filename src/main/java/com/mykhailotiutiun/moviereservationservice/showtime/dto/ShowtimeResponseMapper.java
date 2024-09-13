package com.mykhailotiutiun.moviereservationservice.showtime.dto;

import com.mykhailotiutiun.moviereservationservice.showtime.domain.Showtime;

public class ShowtimeResponseMapper {

    public ShowtimeResponse toShowtimeResponse(Showtime showtime){
        return new ShowtimeResponse(showtime.getId(), showtime.getDate().toString(), showtime.getStartTime().toString(), showtime.getEndTime().toString(), showtime.getAuditoriumId());
    }
}
