package com.mykhailotiutiun.moviereservationservice.seat;

import com.mykhailotiutiun.moviereservationservice.seat.domain.Seat;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatRepository;
import com.mykhailotiutiun.moviereservationservice.seat.domain.SeatServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SeatDomainTest {

    @Mock
    private SeatRepository seatRepository;
    @InjectMocks
    private SeatServiceImpl seatService;

    @Test
    public void getListByShowtimeIdTest() {
        long showtimeId = 2L;
        Seat seat = Seat.builder().id(1L).build();
        when(seatRepository.findAllByShowtimeId(showtimeId)).thenReturn(List.of(seat));
        assertEquals(List.of(seat), seatService.getListByShowtimeId(showtimeId));
    }

    @Test
    public void getListByUserIdTest() {
        long userId = 2L;
        Seat seat = Seat.builder().id(1L).build();
        when(seatRepository.findAllByUserId(userId)).thenReturn(List.of(seat));
        assertEquals(List.of(seat), seatService.getListByUserId(userId));
    }

    @Test
    public void cloneFromAuditoriumToAuditoriumTest() {
        long fromAuditoriumId = 2L;
        long toAuditoriumId = 3L;
        Seat seat = Seat.builder().id(1L).name("1").build();
        when(seatRepository.findAllByAuditoriumId(fromAuditoriumId)).thenReturn(List.of(seat));
        seatService.cloneFromAuditoriumToAuditorium(fromAuditoriumId, toAuditoriumId);

        Seat expectedSeat = Seat.builder()
                .name(seat.getName())
                .availability(false)
                .build();
        verify(seatRepository).createAllToAuditorium(eq(List.of(expectedSeat)), eq(toAuditoriumId));
    }

    @Test
    public void cloneFromAuditoriumToShowtimeTest() {
        long auditoriumId = 2L;
        long showtimeId = 3L;
        Seat seat = Seat.builder().id(1L).name("1").build();
        when(seatRepository.findAllByAuditoriumId(auditoriumId)).thenReturn(List.of(seat));
        seatService.cloneFromAuditoriumToShowtime(auditoriumId, showtimeId);

        Seat expectedSeat = Seat.builder()
                .name(seat.getName())
                .availability(true)
                .build();
        verify(seatRepository, times(1)).createAllToShowtime(eq(List.of(expectedSeat)), eq(showtimeId));
    }

    @Test
    public void reserveSeats() {
        long id = 1L;
        long userId = 2L;
        seatService.reserveSeats(List.of(id), userId);
        verify(seatRepository).reserveSeats(List.of(id), userId);
    }
}
