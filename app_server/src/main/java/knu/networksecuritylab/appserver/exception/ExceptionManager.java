package knu.networksecuritylab.appserver.exception;

import knu.networksecuritylab.appserver.exception.custom.CustomAuthException;
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
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponseDto.builder()
                        .statusCode(e.getErrorCode().getHttpStatus().value())
                        .errorType(e.getErrorCode().name())
                        .message(e.getErrorCode().getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> validExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .errorType(HttpStatus.BAD_REQUEST.name())
                        .message(e.getFieldError().getDefaultMessage())
                        .build());
    }
}
