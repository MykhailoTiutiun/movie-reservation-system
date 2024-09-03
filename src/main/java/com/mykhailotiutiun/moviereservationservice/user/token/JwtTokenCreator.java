package com.mykhailotiutiun.moviereservationservice.user.token;

import com.mykhailotiutiun.moviereservationservice.user.domain.JwtTokenProvider;
import com.mykhailotiutiun.moviereservationservice.user.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenCreator implements JwtTokenProvider {

    private final String secret;

    public JwtTokenCreator(String secret) {
        this.secret = secret;
    }

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        return Jwts.builder().setClaims(claims).setSubject(String.valueOf(user.getId())).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
}
