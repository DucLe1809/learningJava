package org.learningspringwithduc.loginservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

// Jwt Service Class

@Service
public class JwtUtils {
    private static final String jwtSecreteKey = "daylakeybimatcuaducdecothethuchanhgiwti";

    private SecretKey generateSecreteKey() {
        return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60))
                .signWith(generateSecreteKey())
                .compact();
    }

    public String generateUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(generateSecreteKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        String extractedUsername = generateUsernameFromToken(token);
        return username.equals(extractedUsername);
    }

}
