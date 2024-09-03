package com.mykhailotiutiun.moviereservationservice.user.domain;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.exception.PasswordMatchesException;
import com.mykhailotiutiun.moviereservationservice.exception.UnverifiedEmailException;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final VerificationTokenProvider verificationTokenProvider;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailSender mailSender;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, VerificationTokenProvider verificationTokenProvider, VerificationTokenRepository verificationTokenRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.verificationTokenProvider = verificationTokenProvider;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailSender = mailSender;
    }

    @Override
    public String getToken(User user) {
        User userFromDB = userRepository.findByEmail(user.getEmail()).orElseThrow(NotFoundException::new);
        if(!userFromDB.isVerified()){
            throw new UnverifiedEmailException();
        }
        if (!passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())){
            throw new PasswordMatchesException();
        }

        return jwtTokenProvider.generateToken(userFromDB);
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerified(false);
        user.setRole(UserRole.USER);
        userRepository.create(user);

        String verificationToken = verificationTokenProvider.getToken();
        verificationTokenRepository.create(verificationToken, user.getId());

        mailSender.sendUserVerificationToken(verificationToken, user.getEmail());

        return user;
    }

    @Override
    public void verify(String verificationToken) {
        Long userId = verificationTokenRepository.getUserIdByToken(verificationToken).orElseThrow(NotFoundException::new);
        userRepository.verifyUser(userId);
    }
}
