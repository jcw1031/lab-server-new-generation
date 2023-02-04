package knu.networksecuritylab.appserver.config.jwt;

import io.jsonwebtoken.UnsupportedJwtException;
import knu.networksecuritylab.appserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final SecretKey secretKey;

    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!isValidateUri(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization = {}", authorization);

        // Authorization 헤더 검증
        if (isValidateUri(request) && invalidAuthorizationHeader(authorization)) {
            throw new UnsupportedJwtException("Error");
        }

        // Token 추출
        String token = authorization.split(" ")[1];

        // 토큰 정보 추출
        String studentId = JwtUtil.getStudentId(token, secretKey);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(studentId, null,
                        List.of(new SimpleGrantedAuthority("USER"))); // DB에서 회원의 role을 꺼내서 넣을 수 있음

        // 디테일 추가
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    private boolean isValidateUri(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        return !(requestURI.equals("/api/v1/users/sign-up") || requestURI.equals("/api/v1/users/sign-in")) &&
                method.equals("POST");
    }

    private boolean invalidAuthorizationHeader(String authorization) {
        return authorization == null || !authorization.startsWith(TOKEN_PREFIX);
    }
}
