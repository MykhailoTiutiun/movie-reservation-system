package com.mykhailotiutiun.moviereservationservice.showtime.dto;

public record UpdateShowtimeRequest(Long id, String date, String startTime, String endTime) {
}
