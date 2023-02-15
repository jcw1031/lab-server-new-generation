package knu.networksecuritylab.appserver.exception.user;

public class UsernameDuplicateException extends UserException {

    public UsernameDuplicateException(UserErrorCode userErrorCode) {
        super(userErrorCode);
    }
}
