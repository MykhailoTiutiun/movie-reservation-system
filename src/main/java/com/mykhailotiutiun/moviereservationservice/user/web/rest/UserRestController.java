package com.mykhailotiutiun.moviereservationservice.user.web.rest;

import com.mykhailotiutiun.moviereservationservice.user.domain.User;
import com.mykhailotiutiun.moviereservationservice.user.domain.UserService;
import com.mykhailotiutiun.moviereservationservice.user.dto.RegisterRequest;
import com.mykhailotiutiun.moviereservationservice.user.dto.TokenRequest;
import com.mykhailotiutiun.moviereservationservice.user.dto.TokenResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/users")
public class UserRestController {
    private final UserService userService;

    @Value("${mail.success-redirect-url}")
    private String successRedirectUrl;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest){
        User user = User.builder()
                .email(registerRequest.email())
                .password(registerRequest.password())
                .build();
        userService.register(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/request-token")
    public ResponseEntity<TokenResponse> requestToken(@RequestBody @Valid TokenRequest tokenRequest){
        User user = User.builder()
                .email(tokenRequest.email())
                .password(tokenRequest.password())
                .build();
        TokenResponse tokenResponse = new TokenResponse(userService.getToken(user));
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam(name = "token") String token, HttpServletResponse response) throws IOException {
        userService.verify(token);
        response.sendRedirect(successRedirectUrl);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
