package knu.networksecuritylab.appserver.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final SecretKey secretKey;
    private final Long tokenExpireTimeMs = 1_000 * 60L;

    public String createToken(Long id, String studentId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(studentId);
        claims.put("id", id);
        claims.put("roles", roles);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpireTimeMs))
                .signWith(secretKey)
                .compact();
    }
}
