package com.mykhailotiutiun.moviereservationservice.movie.web.rest;

import com.mykhailotiutiun.moviereservationservice.movie.domain.Movie;
import com.mykhailotiutiun.moviereservationservice.movie.domain.MovieService;
import com.mykhailotiutiun.moviereservationservice.movie.dto.CreateMovieRequest;
import com.mykhailotiutiun.moviereservationservice.movie.dto.GenreToMovieRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(movieService.getById(id), HttpStatus.OK);
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
                .imageId(createMovieRequest.imageId())
                .build();
        return new ResponseEntity<>(movieService.create(movie), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Movie> update(@RequestBody @Valid UpdateMovieRequest updateMovieRequest){
        Movie movie = Movie.builder()
                .id(updateMovieRequest.id())
                .title(updateMovieRequest.title())
                .description(updateMovieRequest.description())
                .imageId(updateMovieRequest.imageId())
                .build();
        return new ResponseEntity<>(movieService.update(movie), HttpStatus.OK);
    }

    @PutMapping("/addGenre")
    public ResponseEntity<?> addGenre(@RequestBody @Valid GenreToMovieRequest genreToMovieRequest){
        movieService.addGenre(genreToMovieRequest.movieId(), genreToMovieRequest.genreId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/removeGenre")
    public ResponseEntity<?> removeGenre(@RequestBody @Valid GenreToMovieRequest genreToMovieRequest){
        movieService.removeGenre(genreToMovieRequest.movieId(), genreToMovieRequest.genreId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        movieService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
