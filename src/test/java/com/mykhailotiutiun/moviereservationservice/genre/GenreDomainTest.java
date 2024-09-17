package com.mykhailotiutiun.moviereservationservice.genre;

import com.mykhailotiutiun.moviereservationservice.genre.domain.Genre;
import com.mykhailotiutiun.moviereservationservice.genre.domain.GenreRepository;
import com.mykhailotiutiun.moviereservationservice.genre.domain.GenreServiceImpl;
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
public class GenreDomainTest {

    @Mock
    private GenreRepository genreRepository;
    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    public void getListTest(){
        Genre genre = Genre.builder().id(1L).build();
        when(genreRepository.findAll()).thenReturn(List.of(genre));
        assertEquals(List.of(genre), genreService.getList());
    }

    @Test
    public void createTest(){
        Genre genre = Genre.builder().id(1L).build();
        genreService.create(genre);
        verify(genreRepository).create(genre);
    }
    @Test
    public void updateTest(){
        Genre genre = Genre.builder().id(1L).build();
        genreService.update(genre);
        verify(genreRepository).update(genre);
    }
    @Test
    public void deleteByIdTest(){
        long id = 1L;
        genreService.deleteById(id);
        verify(genreRepository).deleteById(id);
    }
}
