package com.mykhailotiutiun.moviereservationservice.auditorium.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddAuditoriumToMovieRequest(@NotNull @Min(1) @Max(3) Long auditoriumId, @NotNull @Min(1) Long movieId) {
}
