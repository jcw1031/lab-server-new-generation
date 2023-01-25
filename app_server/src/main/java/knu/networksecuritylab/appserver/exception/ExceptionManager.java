package knu.networksecuritylab.appserver.exception;

import knu.networksecuritylab.appserver.exception.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDTO> authExceptionHandler(AuthException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponseDTO.builder()
                        .statusCode(e.getErrorCode().getHttpStatus().value())
                        .errorType(e.getErrorCode().name())
                        .message(e.getErrorCode().getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> validExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .errorType(HttpStatus.BAD_REQUEST.name())
                        .message(e.getFieldError().getDefaultMessage())
                        .build());
    }
}
