package knu.networksecuritylab.appserver.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final Long expireTimeMs = 1000 * 60 * 60L;

    public static String createToken(Long id, String studentId, SecretKey secretKey) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("studentId", studentId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(secretKey)
                .compact();
    }
}
