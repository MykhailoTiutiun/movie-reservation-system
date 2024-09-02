package com.mykhailotiutiun.moviereservationservice.user;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.exception.PasswordMatchesException;
import com.mykhailotiutiun.moviereservationservice.user.domain.*;
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
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getTokenTest() {
        String expectedToken = "Token";
        User user = User.builder().id(1L).username("1").password("1").build();
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(user.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtTokenProvider.generateToken(user)).thenReturn(expectedToken);
        assertEquals(expectedToken, userService.getToken(user));

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getToken(user));

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(user.getPassword(), user.getPassword())).thenReturn(false);
        assertThrows(PasswordMatchesException.class, () -> userService.getToken(user));
    }

    @Test
    public void createTest() {
        User user = User.builder().id(1L).password("toEncode").build();
        when(passwordEncoder.encode("toEncode")).thenReturn("fromEncode");
        userService.register(user);
        verify(userRepository).create(user);
    }
}
