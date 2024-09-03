package com.mykhailotiutiun.moviereservationservice.user.mail;

import com.mykhailotiutiun.moviereservationservice.user.domain.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSenderImpl implements MailSender {

    private final JavaMailSender mailSender;
    private final String fromEmail;
    private final String redirectURL;

    public MailSenderImpl(JavaMailSender mailSender, String fromEmail, String redirectURL) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
        this.redirectURL = redirectURL;
    }

    @Override
    public void sendUserVerificationToken(String token, String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject("Email confirmation");
        mailMessage.setText("Confirm email: " + redirectURL + "?token=" + token);
        mailSender.send(mailMessage);
    }
}
