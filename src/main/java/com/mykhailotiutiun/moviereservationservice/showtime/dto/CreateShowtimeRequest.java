package com.mykhailotiutiun.moviereservationservice.showtime.dto;

public record CreateShowtimeRequest(String date, String startTime, String endTime, Long auditoriumId) {
}
