package knu.networksecuritylab.appserver.exception.user.impl;

import knu.networksecuritylab.appserver.exception.user.UserErrorCode;
import knu.networksecuritylab.appserver.exception.user.UserException;

public class InvalidAuthenticationException extends UserException {

    public InvalidAuthenticationException() {
        super(UserErrorCode.INVALID_AUTHENTICATION);
    }
}
