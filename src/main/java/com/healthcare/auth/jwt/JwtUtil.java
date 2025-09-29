package com.healthcare.auth.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;
import java.util.logging.Logger;

@Component
@Slf4j
public class JwtUtil {


    private static String SECRET_KEY = "f456e2035bb10cd9b599c1c4e953ca9fadd11dfd1ea8a88c6b04261cc76650bf0acc354f29a265b955f8921173939e9021bf51fcebb90125b66a433b656bb4c4";
    private static final long EXPIRATION = 36000000L;
    private final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000;

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            return false;
        }
    }
    public String generateToken(String email, String role){
        return
                Jwts.builder()
                        .setSubject(email) // set the subject
                        .claim("role",role) // set a claim
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // set an expiration date
                        .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                        .compact();
    }

    public String generateRefreshToken(String email){
        return
                Jwts.builder()
                        .setSubject(email)
                        .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                        .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                        .compact();
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return
                Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJwt(token)
                        .getBody();
    }


}
