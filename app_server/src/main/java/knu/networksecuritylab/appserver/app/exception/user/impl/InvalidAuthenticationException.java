package knu.networksecuritylab.appserver.app.exception.user.impl;

import knu.networksecuritylab.appserver.app.exception.user.UserErrorCode;
import knu.networksecuritylab.appserver.app.exception.user.UserException;

public class InvalidAuthenticationException extends UserException {

    public InvalidAuthenticationException() {
        super(UserErrorCode.INVALID_AUTHENTICATION);
    }
}
