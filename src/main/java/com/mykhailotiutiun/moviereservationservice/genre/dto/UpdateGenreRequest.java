package com.mykhailotiutiun.moviereservationservice.genre.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateGenreRequest(@NotNull @Min(1) Long id, @NotNull @NotBlank String name) {
}
