package knu.networksecuritylab.appserver.exception.handler;

import knu.networksecuritylab.appserver.exception.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> validExceptionHandler(
            MethodArgumentNotValidException e, HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> messages.add(fieldError.getDefaultMessage()));

        ErrorResponseDto errorResponseDto = createErrorResponseDto(
                HttpStatus.BAD_REQUEST, messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponseDto> jsonParseExceptionHandler(HttpServletRequest request) {
        List<String> messages = new ArrayList<>();
        messages.add("HTTP 바디 메시지를 읽을 수 없습니다.");
        log.error("Messages = {}", messages);

        ErrorResponseDto errorResponseDto = createErrorResponseDto(
                HttpStatus.BAD_REQUEST, messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponseDto> methodNotAllowedExceptionHandler(HttpServletRequest request) {
        List<String> messages = new ArrayList<>();
        messages.add("지원하지 않는 Method입니다.");
        log.error("Messages = {}", messages);

        ErrorResponseDto errorResponseDto = createErrorResponseDto(
                HttpStatus.BAD_REQUEST, messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponseDto> argumentMismatchExceptionHandler(
            HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        messages.add("경로 변수 타입이 올바르지 않습니다.");
        log.error("Messages = {}", messages);

        ErrorResponseDto errorResponseDto = createErrorResponseDto(
                HttpStatus.BAD_REQUEST, messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponseDto> constraintViolationExceptionHandler(
            ConstraintViolationException e, HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        messages.add(e.getLocalizedMessage());
        log.error("Messages = {}", messages);

        ErrorResponseDto errorResponseDto = createErrorResponseDto(
                HttpStatus.BAD_REQUEST, messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResponseDto> missingRequestParameterExceptionHandler(
        HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        messages.add("파라미터가 누락되었습니다.");
        log.error("Messages = {}", messages);

        ErrorResponseDto errorResponseDto = createErrorResponseDto(
                HttpStatus.BAD_REQUEST, messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    private ErrorResponseDto createErrorResponseDto(
            HttpStatus status, List<String> messages, HttpServletRequest request
    ) {
        String requestURI = request.getRequestURI();

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(status.value())
                .errorType(status.name())
                .path(requestURI)
                .build();
        messages.forEach(errorResponseDto::addMessage);
        return errorResponseDto;
    }
}
