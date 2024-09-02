package com.mykhailotiutiun.moviereservationservice.auditorium.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddAuditoriumToMovieRequest(@NotNull @Min(1) Long auditoriumId, @NotNull @Min(1) Long movieId) {
}
