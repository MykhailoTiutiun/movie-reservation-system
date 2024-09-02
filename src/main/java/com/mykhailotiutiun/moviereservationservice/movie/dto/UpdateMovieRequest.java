package com.mykhailotiutiun.moviereservationservice.movie.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateMovieRequest (@NotNull @Min(1) Long id, @NotNull @NotBlank String title, @NotNull @NotBlank String description){
}
