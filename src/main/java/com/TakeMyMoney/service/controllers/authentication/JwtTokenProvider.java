package com.TakeMyMoney.service.controllers.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${app.auth.jwtSecret}")
    private String jwtSecret = "${JWT_SECRET:take-my-money-service!0941240918358687214785814187872726}";

    @Value("${app.auth.jwtExpirationInMs}")
    private Long jwtExpiration;

    private SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    public String generateToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", List.of())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
