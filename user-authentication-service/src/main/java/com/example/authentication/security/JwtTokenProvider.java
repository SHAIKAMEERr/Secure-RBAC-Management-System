package com.example.authentication.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")  // Get the secret key from application.properties
    private String SECRET_KEY;

    private static final long EXPIRATION_TIME = 86400000L; // 24 hours

    // Method to create a JWT token with HMAC-SHA-256
    public String createToken(String username, String string) {
        try {
            // Build the JWT token using HMAC-SHA-256
            return Jwts.builder()
                    .setSubject(username)
                    .claim("roles", string)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())  // HMAC-SHA-256
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    // Method to validate the token (to be used in the filter/interceptor for each request)
    public boolean validateToken(String token) {
        try {
            // Parse the token with the secret key (HMAC-SHA-256)
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Method to get the username from the token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
