package com.mykhailotiutiun.moviereservationservice.showtime.web.rest;

import com.mykhailotiutiun.moviereservationservice.showtime.domain.Showtime;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeService;
import com.mykhailotiutiun.moviereservationservice.showtime.dto.CreateShowtimeRequest;
import com.mykhailotiutiun.moviereservationservice.showtime.dto.ShowtimeResponse;
import com.mykhailotiutiun.moviereservationservice.showtime.dto.ShowtimeResponseMapper;
import com.mykhailotiutiun.moviereservationservice.showtime.dto.UpdateShowtimeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/v1/showtimes")
public class ShowtimeRestController {

    private final ShowtimeService showtimeService;
    private final ShowtimeResponseMapper showtimeResponseMapper;

    public ShowtimeRestController(ShowtimeService showtimeService, ShowtimeResponseMapper showtimeResponseMapper) {
        this.showtimeService = showtimeService;
        this.showtimeResponseMapper = showtimeResponseMapper;
    }

    @GetMapping
    public ResponseEntity<List<ShowtimeResponse>> getList(@RequestParam("auditoriumId") Long auditoriumId, @RequestParam(value = "date", required = false) String stringDate) {
        List<Showtime> showtimes;
        if(stringDate == null) {
            showtimes = showtimeService.getList(auditoriumId);
        } else {
            showtimes = showtimeService.getList(auditoriumId, LocalDate.parse(stringDate));

        }
        List<ShowtimeResponse> showtimeResponses = showtimes.stream().map(showtimeResponseMapper::toShowtimeResponse).toList();
        return new ResponseEntity<>(showtimeResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(showtimeService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShowtimeResponse> create(@RequestBody CreateShowtimeRequest createShowtimeRequest){
        Showtime showtime = Showtime.builder()
                .date(LocalDate.parse(createShowtimeRequest.date()))
                .startTime(LocalTime.parse(createShowtimeRequest.startTime()))
                .endTime(LocalTime.parse(createShowtimeRequest.endTime()))
                .auditoriumId(createShowtimeRequest.auditoriumId())
                .build();
        showtimeService.create(showtime);
        return new ResponseEntity<>(showtimeResponseMapper.toShowtimeResponse(showtime), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ShowtimeResponse> update(@RequestBody UpdateShowtimeRequest updateShowtimeRequest){
        Showtime showtime = Showtime.builder()
                .id(updateShowtimeRequest.id())
                .date(LocalDate.parse(updateShowtimeRequest.date()))
                .startTime(LocalTime.parse(updateShowtimeRequest.startTime()))
                .endTime(LocalTime.parse(updateShowtimeRequest.endTime()))
                .build();
        showtimeService.update(showtime);
        return new ResponseEntity<>(showtimeResponseMapper.toShowtimeResponse(showtime), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        showtimeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
