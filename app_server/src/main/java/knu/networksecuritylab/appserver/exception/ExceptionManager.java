package knu.networksecuritylab.appserver.exception;

import knu.networksecuritylab.appserver.exception.dto.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
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
}
