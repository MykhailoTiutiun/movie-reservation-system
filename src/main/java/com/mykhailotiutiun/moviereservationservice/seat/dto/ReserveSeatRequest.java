package com.mykhailotiutiun.moviereservationservice.seat.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReserveSeatRequest(@NotNull @Min(1) Long seatId, @NotNull @Min(1) Long userId) {
}
