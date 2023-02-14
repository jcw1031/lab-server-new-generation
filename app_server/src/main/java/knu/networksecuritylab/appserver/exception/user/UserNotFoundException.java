package knu.networksecuritylab.appserver.exception.user;

public class UserNotFoundException extends UserException {

    public UserNotFoundException(UserErrorCode userErrorCode) {
        super(userErrorCode);
    }
}
