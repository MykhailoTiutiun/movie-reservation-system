package com.mykhailotiutiun.moviereservationservice.seat.web.rest;

import com.mykhailotiutiun.moviereservationservice.seat.domain.Seat;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatService;
import com.mykhailotiutiun.moviereservationservice.seat.dto.ReserveSeatRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/seats")
public class SeatRestController {

    private final SeatService seatService;

    public SeatRestController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<List<Seat>> getList(@RequestParam(value = "showtimeId", required = false) Long showtimeId,
                                              @RequestParam(value = "userId", required = false) Long userId) {
        if((showtimeId == null) == (userId == null)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (showtimeId != null) {
            return new ResponseEntity<>(seatService.getListByShowtimeId(showtimeId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(seatService.getListByUserId(userId), HttpStatus.OK);
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveSeat(@RequestBody ReserveSeatRequest request){
        seatService.reserveSeat(request.seatId(), request.userId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
