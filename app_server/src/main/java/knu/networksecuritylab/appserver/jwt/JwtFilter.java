package knu.networksecuritylab.appserver.jwt;

import knu.networksecuritylab.appserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final SecretKey secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization = {}", authorization);

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization이 올바르지 않습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // Token 추출
        String token = authorization.split(" ")[1];

        // Token Expired 여부 확인
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("Token이 만료되었습니다.");
            filterChain.doFilter(request, response);
        }

        // studentId 추출
        String studentId = JwtUtil.getStudentId(token, secretKey);
        log.info("studentId = {}", studentId);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(studentId, null, List.of(new SimpleGrantedAuthority("USER"))); // DB에서 회원의 role을 꺼내서 넣을 수 있음

        // 디테일 추가
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
