package knu.networksecuritylab.appserver.exception.user.impl;

import knu.networksecuritylab.appserver.exception.user.UserErrorCode;
import knu.networksecuritylab.appserver.exception.user.UserException;

public class UserNotFoundException extends UserException {

    public UserNotFoundException(UserErrorCode userErrorCode) {
        super(userErrorCode);
    }
}
