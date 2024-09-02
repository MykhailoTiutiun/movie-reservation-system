package com.mykhailotiutiun.moviereservationservice.movie.web.rest;

import com.mykhailotiutiun.moviereservationservice.movie.domain.Movie;
import com.mykhailotiutiun.moviereservationservice.movie.domain.MovieService;
import com.mykhailotiutiun.moviereservationservice.movie.dto.CreateMovieRequest;
import com.mykhailotiutiun.moviereservationservice.movie.dto.UpdateMovieRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movies")
public class MovieRestController {

    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getList(){
        return new ResponseEntity<>(movieService.getList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody @Valid CreateMovieRequest createMovieRequest){
        Movie movie = Movie.builder()
                .title(createMovieRequest.title())
                .description(createMovieRequest.description())
                .build();
        return new ResponseEntity<>(movieService.create(movie), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Movie> update(@RequestBody @Valid UpdateMovieRequest updateMovieRequest){
        Movie movie = Movie.builder()
                .id(updateMovieRequest.id())
                .title(updateMovieRequest.title())
                .description(updateMovieRequest.description())
                .build();
        return new ResponseEntity<>(movieService.update(movie), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        movieService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
