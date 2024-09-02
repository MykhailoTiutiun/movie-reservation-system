package com.mykhailotiutiun.moviereservationservice.showtime.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateShowtimeRequest(@NotNull @Min(1) Long id, @NotNull String date, @NotNull String startTime, @NotNull String endTime) {
}
