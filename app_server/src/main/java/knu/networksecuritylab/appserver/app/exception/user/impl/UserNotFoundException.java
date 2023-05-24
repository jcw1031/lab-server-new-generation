package knu.networksecuritylab.appserver.app.exception.user.impl;

import knu.networksecuritylab.appserver.app.exception.user.UserErrorCode;
import knu.networksecuritylab.appserver.app.exception.user.UserException;

public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
