package knu.networksecuritylab.appserver.exception.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import knu.networksecuritylab.appserver.exception.ErrorCode;
import knu.networksecuritylab.appserver.exception.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error("토큰 만료");
            setErrorResponse(response, ErrorCode.TOKEN_EXPIRED);
        } catch (SignatureException e) {
            log.error("토큰 유효성 없음");
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        } catch (MalformedJwtException e) {
            log.error("훼손된 토큰");
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.error("지원하지 않는 토큰 형식");
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(mapper.writeValueAsString(ErrorResponseDto
                .builder()
                .statusCode(errorCode.getHttpStatus().value())
                .errorType(errorCode.name())
                .message(errorCode.getMessage())
                .build()));
    }
}
