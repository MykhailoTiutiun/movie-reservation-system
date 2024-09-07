package com.mykhailotiutiun.moviereservationservice.genres.web.rest;

import com.mykhailotiutiun.moviereservationservice.genres.domain.Genre;
import com.mykhailotiutiun.moviereservationservice.genres.domain.GenreService;
import com.mykhailotiutiun.moviereservationservice.genres.dto.CreateGenreRequest;
import com.mykhailotiutiun.moviereservationservice.genres.dto.UpdateGenreRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/genres")
public class GenreRestController {

    private final GenreService genreService;

    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getList(){
        return new ResponseEntity<>(genreService.getList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> create(@RequestBody @Valid CreateGenreRequest createGenreRequest){
        Genre genre = Genre.builder()
                .name(createGenreRequest.name())
                .build();
        genreService.create(genre);
        return new ResponseEntity<>(genre, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Genre> update(@RequestBody @Valid UpdateGenreRequest updateGenreRequest){
        Genre genre = Genre.builder()
                .id(updateGenreRequest.id())
                .name(updateGenreRequest.name())
                .build();
        genreService.update(genre);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        genreService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
