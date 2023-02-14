package knu.networksecuritylab.appserver.exception.user;

public class InvalidAuthenticationException extends UserException {

    public InvalidAuthenticationException(UserErrorCode userErrorCode) {
        super(userErrorCode);
    }
}
