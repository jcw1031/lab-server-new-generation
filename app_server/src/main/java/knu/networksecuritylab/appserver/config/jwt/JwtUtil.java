package knu.networksecuritylab.appserver.config.jwt;

import io.jsonwebtoken.Jwts;
import knu.networksecuritylab.appserver.entity.authority.Role;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    public static boolean isExpired(String token, SecretKey secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .after(new Date());
    }

    public static String getStudentId(String token, SecretKey secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("studentId", String.class);
    }

    public static Role getRole(String token, SecretKey secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", Role.class);
    }
}
