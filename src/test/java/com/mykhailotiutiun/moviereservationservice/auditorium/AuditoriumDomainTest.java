package com.mykhailotiutiun.moviereservationservice.auditorium;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumRepository;
import com.mykhailotiutiun.moviereservationservice.auditorium.domain.AuditoriumServiceImpl;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuditoriumDomainTest {

    @Mock
    private AuditoriumRepository auditoriumRepository;
    @InjectMocks
    private AuditoriumServiceImpl auditoriumService;

    @Test
    public void getByIdTest() {
        Auditorium auditorium = Auditorium.builder().id(1L).build();
        when(auditoriumRepository.findById(auditorium.getId())).thenReturn(Optional.of(auditorium));
        assertEquals(auditorium, auditoriumService.getById(auditorium.getId()));

        when(auditoriumRepository.findById(auditorium.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> auditoriumService.getById(auditorium.getId()));
    }

    @Test
    public void getListTest() {
        Auditorium auditorium = Auditorium.builder().id(1L).build();
        when(auditoriumRepository.findAll()).thenReturn(List.of(auditorium));
        assertEquals(List.of(auditorium), auditoriumService.getList());
    }
}
