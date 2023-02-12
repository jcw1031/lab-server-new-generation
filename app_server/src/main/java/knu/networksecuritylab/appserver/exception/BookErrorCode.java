package knu.networksecuritylab.appserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookErrorCode {

    BOOK_DUPLICATE(HttpStatus.CONFLICT, "이미 등록된 도서입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
