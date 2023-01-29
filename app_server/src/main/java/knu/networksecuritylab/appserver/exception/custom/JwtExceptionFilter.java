package knu.networksecuritylab.appserver.exception.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import knu.networksecuritylab.appserver.exception.ErrorCode;
import knu.networksecuritylab.appserver.exception.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            setErrorResponse(response);
        }
    }

    private void setErrorResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(mapper.writeValueAsString(ErrorResponseDto
                .builder()
                .statusCode(ErrorCode.TOKEN_EXPIRED.getHttpStatus().value())
                .errorType(ErrorCode.TOKEN_EXPIRED.name())
                .message(ErrorCode.TOKEN_EXPIRED.getMessage())
                .build()));
    }
}
