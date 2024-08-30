package com.mykhailotiutiun.moviereservationservice.user;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.exceptions.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.movie.domain.Movie;
import com.mykhailotiutiun.moviereservationservice.user.domain.User;
import com.mykhailotiutiun.moviereservationservice.user.domain.UserRepository;
import com.mykhailotiutiun.moviereservationservice.user.domain.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDomainTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getByUsername(){
        User user = User.builder().id(1L).username("1").build();
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertEquals(user, userService.getByUsername(user.getUsername()));

        when(userRepository.findByUsername(user.getUsername())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> userService.getByUsername(user.getUsername()));
    }

    @Test
    public void createTest(){
        User user = User.builder().id(1L).build();
        userService.create(user);
        verify(userRepository).create(user);
    }
}
