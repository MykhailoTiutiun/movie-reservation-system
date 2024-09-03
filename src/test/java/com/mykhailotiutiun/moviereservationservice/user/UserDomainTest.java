package com.mykhailotiutiun.moviereservationservice.user;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.exception.PasswordMatchesException;
import com.mykhailotiutiun.moviereservationservice.exception.UnverifiedEmailException;
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
    @Mock
    private VerificationTokenProvider verificationTokenProvider;
    @Mock
    private VerificationTokenRepository verificationTokenRepository;
    @Mock
    private MailSender mailSender;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getTokenTest() {
        String expectedToken = "Token";
        User user = User.builder()
                .id(1L)
                .email("1")
                .password("1")
                .verified(true)
                .build();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(user.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtTokenProvider.generateToken(user)).thenReturn(expectedToken);
        assertEquals(expectedToken, userService.getToken(user));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getToken(user));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(user.getPassword(), user.getPassword())).thenReturn(false);
        assertThrows(PasswordMatchesException.class, () -> userService.getToken(user));

        user.setVerified(false);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertThrows(UnverifiedEmailException.class, () -> userService.getToken(user));
    }

    @Test
    public void createTest() {
        String token = "Token";
        String fromEncodePassword = "fromEncodePassword";
        User user = User.builder().id(1L).password("toEncode").email("email").build();
        when(passwordEncoder.encode("toEncode")).thenReturn(fromEncodePassword);
        when(verificationTokenProvider.getToken()).thenReturn(token);
        userService.register(user);
        assertEquals(fromEncodePassword, user.getPassword());
        verify(verificationTokenRepository).create(token, user.getId());
        verify(mailSender).sendUserVerificationToken(token, user.getEmail());
        verify(userRepository).create(user);
    }

    @Test
    public void verifyTest(){
        long userId = 1L;
        String token = "Token";
        when(verificationTokenRepository.getUserIdByToken(token)).thenReturn(Optional.of(userId));
        userService.verify(token);
        verify(userRepository).verifyUser(userId);
    }
}
