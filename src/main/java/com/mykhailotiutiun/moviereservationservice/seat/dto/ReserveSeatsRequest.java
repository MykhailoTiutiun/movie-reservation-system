package com.mykhailotiutiun.moviereservationservice.seat.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReserveSeatsRequest(@NotNull @Min(1) List<Long> seatIds, @NotNull @Min(1) Long userId) {
}
