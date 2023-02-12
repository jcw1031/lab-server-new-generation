package knu.networksecuritylab.appserver.exception.handler;

import knu.networksecuritylab.appserver.exception.CustomAuthException;
import knu.networksecuritylab.appserver.exception.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(CustomAuthException.class)
    public ResponseEntity<ErrorResponseDto> authExceptionHandler(
            CustomAuthException e, HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        messages.add(e.getErrorCode().getMessage());

        return createResponseEntity(e.getErrorCode().getHttpStatus(), messages, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> validExceptionHandler(
            MethodArgumentNotValidException e, HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> messages.add(fieldError.getDefaultMessage()));

        return createResponseEntity(HttpStatus.BAD_REQUEST, messages, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponseDto> jsonParseExceptionHandler(HttpServletRequest request) {
        String message = "HTTP 바디 메시지를 읽을 수 없습니다.";

        List<String> messages = new ArrayList<>();
        messages.add(message);

        log.error("Messages = {}", messages);

        return createResponseEntity(HttpStatus.BAD_REQUEST, messages, request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponseDto> methodNotAllowedExceptionHandler(HttpServletRequest request) {
        String message = "지원하지 않는 Method입니다.";

        List<String> messages = new ArrayList<>();
        messages.add(message);
        log.error("Messages = {}", messages);

        return createResponseEntity(HttpStatus.BAD_REQUEST, messages, request);
    }

    private ResponseEntity<ErrorResponseDto> createResponseEntity(
            HttpStatus status, List<String> messages, HttpServletRequest request
    ) {
        String requestURI = request.getRequestURI();

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(status.value())
                .errorType(status.name())
                .path(requestURI)
                .build();
        messages.forEach(errorResponseDto::addMessage);

        return ResponseEntity.status(status).body(errorResponseDto);
    }
}
