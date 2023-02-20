package knu.networksecuritylab.appserver.exception.handler;

import knu.networksecuritylab.appserver.exception.file.FileException;
import knu.networksecuritylab.appserver.exception.handler.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(FileException.class)
    protected ResponseEntity<ErrorResponseDto> fileExceptionHandler(
            FileException e, HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        messages.add(e.getFileErrorCode().getMessage());
        log.error("Messages = {}", messages);

        ErrorResponseDto errorResponseDto = createErrorResponseDto(
                e.getFileErrorCode().getHttpStatus(), messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<ErrorResponseDto> fileSizeExceptionHandler(
            HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        messages.add("파일의 용량이 10MB를 초과합니다.");
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
