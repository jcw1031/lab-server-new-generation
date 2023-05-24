package knu.networksecuritylab.appserver.app.exception.user.impl;

import knu.networksecuritylab.appserver.app.exception.user.UserErrorCode;
import knu.networksecuritylab.appserver.app.exception.user.UserException;

public class InvalidUsernameOrPassword extends UserException {

    public InvalidUsernameOrPassword() {
        super(UserErrorCode.INVALID_USERNAME_OR_PASSWORD);
    }
}
