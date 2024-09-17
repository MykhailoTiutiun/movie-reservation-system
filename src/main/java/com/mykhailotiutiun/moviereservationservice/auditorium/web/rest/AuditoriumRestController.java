package com.mykhailotiutiun.moviereservationservice.auditorium.web.rest;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/auditoriums")
public class AuditoriumRestController {

    private final AuditoriumService auditoriumService;

    public AuditoriumRestController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auditorium> getById(@PathVariable Long id){
        return new ResponseEntity<>(auditoriumService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Auditorium>> getList(){
        return new ResponseEntity<>(auditoriumService.getList(), HttpStatus.OK);
    }
}
