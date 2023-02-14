package knu.networksecuritylab.appserver.exception.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BookException extends RuntimeException {

    private final BookErrorCode bookErrorCode;
}
