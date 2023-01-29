package knu.networksecuritylab.appserver.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import knu.networksecuritylab.appserver.entity.authority.Role;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    private static final Long EXPIRE_TIME_MS = 1_000 * 5L;
    private static final String TOKEN_PREFIX = "Bearer ";

    public static String createToken(Long id, String studentId, Role role, final SecretKey secretKey) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("studentId", studentId);
        claims.put("role", role);

        return TOKEN_PREFIX + Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME_MS))
                .signWith(secretKey)
                .compact();
    }
}
