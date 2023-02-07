package knu.networksecuritylab.appserver.exception.handler;

import knu.networksecuritylab.appserver.exception.CustomAuthException;
import knu.networksecuritylab.appserver.exception.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(CustomAuthException.class)
    public ResponseEntity<ErrorResponseDto> authExceptionHandler(CustomAuthException e) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(e.getErrorCode().getHttpStatus().value())
                .errorType(e.getErrorCode().name())
                .build();
        errorResponseDto.addMessage(e.getErrorCode().getMessage());

        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(errorResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> validExceptionHandler(MethodArgumentNotValidException e) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorType(HttpStatus.BAD_REQUEST.name())
                .build();
        e.getFieldErrors().forEach(fieldError ->
                errorResponseDto.addMessage(fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
}
