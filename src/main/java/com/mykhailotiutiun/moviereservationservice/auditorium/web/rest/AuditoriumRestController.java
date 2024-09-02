package com.mykhailotiutiun.moviereservationservice.auditorium.web.rest;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumService;
import com.mykhailotiutiun.moviereservationservice.auditorium.dto.AddAuditoriumToMovieRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/auditoriums")
public class AuditoriumRestController {

    private final AuditoriumService auditoriumService;

    public AuditoriumRestController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @GetMapping
    public ResponseEntity<List<Auditorium>> getByMovieId(@RequestParam(required = false) Long movieId){
        return new ResponseEntity<>(auditoriumService.getListByMovieId(movieId), HttpStatus.OK);
    }

    @PostMapping("/clone-to-movie")
    public ResponseEntity<Auditorium> cloneToMovie(@RequestBody @Valid AddAuditoriumToMovieRequest request){
        return new ResponseEntity<>(auditoriumService.cloneToMovie(request.auditoriumId(), request.movieId()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        auditoriumService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
