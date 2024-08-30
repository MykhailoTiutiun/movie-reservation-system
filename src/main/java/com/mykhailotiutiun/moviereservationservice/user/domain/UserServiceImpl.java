package com.mykhailotiutiun.moviereservationservice.user.domain;

import com.mykhailotiutiun.moviereservationservice.exceptions.NotFoundException;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }
}
