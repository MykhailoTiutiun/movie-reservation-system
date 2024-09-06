package com.mykhailotiutiun.moviereservationservice.movie.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GenreToMovieRequest(@NotNull @Min(1) Long movieId, @NotNull @Min(1) Long genreId) {
}
