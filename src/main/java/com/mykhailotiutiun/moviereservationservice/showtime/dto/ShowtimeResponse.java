package com.mykhailotiutiun.moviereservationservice.showtime.dto;

public record ShowtimeResponse(Long id, String date, String startTime, String endTime, Long auditoriumId) {
}
