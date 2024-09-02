package com.mykhailotiutiun.moviereservationservice.showtime.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateShowtimeRequest(@NotNull String date, @NotNull String startTime, @NotNull String endTime, @NotNull @Min(4) Long auditoriumId) {
}
