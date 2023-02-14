package knu.networksecuritylab.appserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookNotFoundException extends RuntimeException {

    private final BookErrorCode bookErrorCode;
}
