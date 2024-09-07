package com.mykhailotiutiun.moviereservationservice.movie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMovieRequest(@NotNull @NotBlank String title, @NotNull @NotBlank String description, Long imageId) {}
