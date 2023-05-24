package knu.networksecuritylab.appserver.app.exception.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class UserException extends RuntimeException {

    private final UserErrorCode userErrorCode;
}
