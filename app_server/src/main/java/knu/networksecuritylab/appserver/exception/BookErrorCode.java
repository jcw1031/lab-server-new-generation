package knu.networksecuritylab.appserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookErrorCode {

    BOOK_DUPLICATE(HttpStatus.CONFLICT, "이미 등록된 도서입니다."),
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 도서가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
