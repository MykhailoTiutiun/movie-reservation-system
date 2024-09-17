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
    public ResponseEntity<List<ShowtimeResponse>> getList(@RequestParam(value = "auditoriumId", required = false) Long auditoriumId,
                                                          @RequestParam(value = "movieId", required = false) Long movieId,
                                                          @RequestParam(value = "date", required = false) String stringDate) {
        if (auditoriumId != null && movieId != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Showtime> showtimes;
        if (auditoriumId != null) {
            if (stringDate == null) {
                showtimes = showtimeService.getListByAuditoriumId(auditoriumId);
            } else {
                showtimes = showtimeService.getListByAuditoriumId(auditoriumId, LocalDate.parse(stringDate));
            }
        } else if(movieId != null) {
            if (stringDate == null){
                showtimes = showtimeService.getListByMovieId(movieId);
            } else {
                showtimes = showtimeService.getListByMovieId(movieId, LocalDate.parse(stringDate));
            }
        } else if(stringDate != null){
            showtimes = showtimeService.getListByDate(LocalDate.parse(stringDate));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<ShowtimeResponse> showtimeResponses = showtimes.stream().map(showtimeResponseMapper::toShowtimeResponse).toList();
        return new ResponseEntity<>(showtimeResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(showtimeService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShowtimeResponse> create(@RequestBody CreateShowtimeRequest createShowtimeRequest) {
        Showtime showtime = Showtime.builder()
                .date(LocalDate.parse(createShowtimeRequest.date()))
                .startTime(LocalTime.parse(createShowtimeRequest.startTime()))
                .endTime(LocalTime.parse(createShowtimeRequest.endTime()))
                .auditoriumId(createShowtimeRequest.auditoriumId())
                .movieId(createShowtimeRequest.movieId())
                .build();
        showtimeService.create(showtime);
        return new ResponseEntity<>(showtimeResponseMapper.toShowtimeResponse(showtime), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ShowtimeResponse> update(@RequestBody UpdateShowtimeRequest updateShowtimeRequest) {
        Showtime showtime = Showtime.builder()
                .id(updateShowtimeRequest.id())
                .date(LocalDate.parse(updateShowtimeRequest.date()))
                .startTime(LocalTime.parse(updateShowtimeRequest.startTime()))
                .endTime(LocalTime.parse(updateShowtimeRequest.endTime()))
                .build();
        showtime = showtimeService.update(showtime);
        return new ResponseEntity<>(showtimeResponseMapper.toShowtimeResponse(showtime), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        showtimeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
