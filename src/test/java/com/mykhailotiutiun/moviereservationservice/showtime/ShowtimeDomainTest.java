package com.mykhailotiutiun.moviereservationservice.showtime;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.Showtime;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeRepository;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ShowtimeServiceImpl;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.ToShowtimeSeatsCloner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowtimeDomainTest {

    @Mock
    private ToShowtimeSeatsCloner showtimeSeatsCloner;
    @Mock
    private ShowtimeRepository showtimeRepository;
    @InjectMocks
    private ShowtimeServiceImpl showtimeService;

    @Test
    public void getListByAuditoriumIdTest() {
        long auditoriumId = 2L;
        Showtime showtime = Showtime.builder().id(1L).build();
        when(showtimeRepository.findAllByAuditoriumId(auditoriumId)).thenReturn(List.of(showtime));
        assertEquals(List.of(showtime), showtimeService.getListByAuditoriumId(auditoriumId));
    }

    @Test
    public void getListByAuditoriumIdAndDateTest() {
        long auditoriumId = 2L;
        LocalDate date = LocalDate.of(2024, 9, 8);
        Showtime showtime = Showtime.builder().id(1L).build();
        when(showtimeRepository.findAllByAuditoriumIdAndDate(auditoriumId, date)).thenReturn(List.of(showtime));
        assertEquals(List.of(showtime), showtimeService.getListByAuditoriumId(auditoriumId, date));
    }

    @Test
    public void getListByMovieIdTest() {
        long movieId = 2L;
        Showtime showtime = Showtime.builder().id(1L).build();
        when(showtimeRepository.findAllByMovieId(movieId)).thenReturn(List.of(showtime));
        assertEquals(List.of(showtime), showtimeService.getListByMovieId(movieId));
    }

    @Test
    public void getListByMovieIdAndDateTest() {
        long movieId = 2L;
        LocalDate date = LocalDate.of(2024, 9, 8);
        Showtime showtime = Showtime.builder().id(1L).build();
        when(showtimeRepository.findAllByMovieIdAndDate(movieId, date)).thenReturn(List.of(showtime));
        assertEquals(List.of(showtime), showtimeService.getListByMovieId(movieId, date));
    }

    @Test
    public void getListDateTest() {
        LocalDate date = LocalDate.of(2024, 9, 8);
        Showtime showtime = Showtime.builder().id(1L).build();
        when(showtimeRepository.findAllByDate(date)).thenReturn(List.of(showtime));
        assertEquals(List.of(showtime), showtimeService.getListByDate(date));
    }

    @Test
    public void getByIdTest() {
        Showtime showtime = Showtime.builder().id(1L).build();
        when(showtimeRepository.findById(showtime.getId())).thenReturn(Optional.of(showtime));
        assertEquals(showtime, showtimeService.getById(showtime.getId()));

        when(showtimeRepository.findById(showtime.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> showtimeService.getById(showtime.getId()));
    }

    @Test
    public void createTest() {
        long auditoriumId = 2L;
        Showtime showtime = Showtime.builder()
                .id(1L)
                .auditoriumId(auditoriumId)
                .build();
        showtimeService.create(showtime);
        verify(showtimeSeatsCloner).cloneFromAuditoriumToShowtime(auditoriumId, showtime.getId());
        verify(showtimeRepository).create(showtime);
    }

    @Test
    public void updateTest() {
        Showtime showtime = Showtime.builder().id(1L).build();
        showtimeService.update(showtime);
        verify(showtimeRepository).update(showtime);
    }

    @Test
    public void deleteByIdTest() {
        long id = 1L;
        showtimeService.deleteById(id);
        verify(showtimeRepository).deleteById(id);
    }
}
