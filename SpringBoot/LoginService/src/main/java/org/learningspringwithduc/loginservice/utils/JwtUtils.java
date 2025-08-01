package org.learningspringwithduc.loginservice.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.learningspringwithduc.loginservice.dtos.LoginResponse;
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

    public String createToken(LoginResponse loginResponse) {
        return Jwts.builder()
                .subject(loginResponse.getId().toString())
                .claim("username", loginResponse.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(generateSecreteKey())
                .compact();
    }

}
