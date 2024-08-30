package com.mykhailotiutiun.moviereservationservice.showtime;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.Showtime;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeRepository;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowtimeDomainTest {

    @Mock
    private ShowtimeRepository showtimeRepository;
    @InjectMocks
    private ShowtimeServiceImpl showtimeService;

    @Test
    public void getListByMovieIdTest(){
        long movieId = 2L;
        Showtime showtime = Showtime.builder().id(1L).build();
        when(showtimeRepository.findAllByMovieId(movieId)).thenReturn(List.of(showtime));
        assertEquals(List.of(showtime), showtimeService.getListByMovieId(movieId));
    }

    @Test
    public void createTest(){
        long movieId = 2L;
        Showtime showtime = Showtime.builder().id(1L).build();
        showtimeService.create(showtime, movieId);
        verify(showtimeRepository).create(showtime, movieId);
    }

    @Test
    public void updateTest(){
        Showtime showtime = Showtime.builder().id(1L).build();
        showtimeService.update(showtime);
        verify(showtimeRepository).update(showtime);
    }

    @Test
    public void deleteByIdTest(){
        long id = 1L;
        showtimeService.deleteById(id);
        verify(showtimeRepository).deleteById(id);
    }
}
