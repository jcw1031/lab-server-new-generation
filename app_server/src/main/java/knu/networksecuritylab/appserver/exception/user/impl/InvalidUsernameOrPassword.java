package knu.networksecuritylab.appserver.exception.user.impl;

import knu.networksecuritylab.appserver.exception.user.UserErrorCode;
import knu.networksecuritylab.appserver.exception.user.UserException;

public class InvalidUsernameOrPassword extends UserException {

    public InvalidUsernameOrPassword(UserErrorCode userErrorCode) {
        super(userErrorCode);
    }
}
