package com.amigoscode.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtil {

    private static final String SECRET_KEY = "foobar_1234567890_foobar_1234567890_foobar_1234567890";

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String generateToken(String subject) {
        return generateToken(subject, new HashMap<>());
    }

    public String generateToken(String subject, String ...roles) {
        Map<String, Object> extraClaims = Map.of("scopes", roles);
        return generateToken(subject, extraClaims);
    }

    public String generateToken(
            String subject,
            Map<String, Object> claims
    ) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("https://amigoscode.com")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(
                        Instant.now().plus(15, ChronoUnit.DAYS))
                )
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
