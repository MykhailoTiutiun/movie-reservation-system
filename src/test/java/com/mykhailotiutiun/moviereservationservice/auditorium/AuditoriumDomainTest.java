package com.mykhailotiutiun.moviereservationservice.auditorium;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumRepository;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumServiceImpl;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.ToAuditoriumSeatsCloner;
import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuditoriumDomainTest {

    @Mock
    private AuditoriumRepository auditoriumRepository;
    @Mock
    private ToAuditoriumSeatsCloner toAuditoriumSeatsCloner;
    @InjectMocks
    private AuditoriumServiceImpl auditoriumService;

    @Test
    public void getByIdTest() {
        Auditorium auditorium = Auditorium.builder().id(1L).build();
        when(auditoriumRepository.findById(auditorium.getId())).thenReturn(Optional.of(auditorium));
        assertEquals(auditorium, auditoriumService.getById(auditorium.getId()));

        when(auditoriumRepository.findById(auditorium.getId())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> auditoriumService.getById(auditorium.getId()));
    }

    @Test
    public void getListByAuditoriumIdTest() {
        long auditoriumId = 2L;
        Auditorium auditorium = Auditorium.builder().id(1L).build();
        when(auditoriumRepository.findAllByMovieId(auditoriumId)).thenReturn(List.of(auditorium));
        assertEquals(List.of(auditorium), auditoriumService.getListByMovieId(auditoriumId));
    }

    @Test
    public void cloneToMovieTest() {
        long movieId = 2L;
        Auditorium auditorium = Auditorium.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .build();
        Auditorium expectedAuditorium = Auditorium.builder()
                .name(auditorium.getName())
                .description(auditorium.getDescription())
                .build();
        when(auditoriumRepository.findById(auditorium.getId())).thenReturn(Optional.of(auditorium));
        auditoriumService.cloneToMovie(auditorium.getId(), movieId);

        verify(toAuditoriumSeatsCloner).cloneFromAuditoriumToAuditorium(eq(auditorium.getId()), eq(null));
        verify(auditoriumRepository).create(eq(expectedAuditorium), eq(movieId));
    }

    @Test
    public void deleteByIdTest() {
        long id = 1L;
        auditoriumService.deleteById(id);
        verify(auditoriumRepository).deleteById(id);
    }
}
