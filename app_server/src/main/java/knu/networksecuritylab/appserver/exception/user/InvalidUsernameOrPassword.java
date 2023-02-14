package knu.networksecuritylab.appserver.exception.user;

public class InvalidUsernameOrPassword extends UserException {

    public InvalidUsernameOrPassword(UserErrorCode userErrorCode) {
        super(userErrorCode);
    }
}
