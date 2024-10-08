package com.mykhailotiutiun.moviereservationservice.movie;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.movie.domain.Movie;
import com.mykhailotiutiun.moviereservationservice.movie.domain.MovieRepository;
import com.mykhailotiutiun.moviereservationservice.movie.domain.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieDomainTest {

    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    public void getListTest() {
        Movie movie = Movie.builder().id(1L).build();
        when(movieRepository.findAll()).thenReturn(List.of(movie));
        assertEquals(List.of(movie), movieService.getList());
    }

    @Test
    public void getByIdTest() {
        Movie movie = Movie.builder().id(1L).build();
        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        assertEquals(movie, movieService.getById(movie.getId()));

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> movieService.getById(movie.getId()));
    }

    @Test
    public void createTest() {
        Movie movie = Movie.builder().id(1L).build();
        movieService.create(movie);
        verify(movieRepository).create(movie);
    }

    @Test
    public void updateTest() {
        Movie movie = Movie.builder().id(1L).build();
        movieService.update(movie);
        verify(movieRepository).update(movie);
    }

    @Test
    public void addGenreTest() {
        Long movieId = 1L;
        Long genreId = 2L;

        movieService.addGenre(movieId, genreId);
        verify(movieRepository).addGenre(movieId, genreId);
    }

    @Test
    public void removeGenreTest(){
        Long movieId = 1L;
        Long genreId = 2L;

        movieService.removeGenre(movieId, genreId);
        verify(movieRepository).removeGenre(movieId, genreId);
    }

    @Test
    public void deleteByIdTest() {
        long id = 1L;
        movieService.deleteById(id);
        verify(movieRepository).deleteById(id);
    }
}
