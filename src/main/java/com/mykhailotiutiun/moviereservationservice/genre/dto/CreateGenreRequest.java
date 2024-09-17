package com.mykhailotiutiun.moviereservationservice.genre.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGenreRequest(@NotNull @NotBlank String name) {
}
