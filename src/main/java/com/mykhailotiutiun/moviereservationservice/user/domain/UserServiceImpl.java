package com.mykhailotiutiun.moviereservationservice.user.domain;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.exception.PasswordMatchesException;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String getToken(User user) {
        User userFromDB = userRepository.findByEmail(user.getEmail()).orElseThrow(NotFoundException::new);
        if (!passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())){
            throw new PasswordMatchesException();
        }

        return jwtTokenProvider.generateToken(userFromDB);
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return userRepository.create(user);
    }
}
