package knu.networksecuritylab.appserver.exception.user.impl;

import knu.networksecuritylab.appserver.exception.user.UserErrorCode;
import knu.networksecuritylab.appserver.exception.user.UserException;

public class UsernameDuplicateException extends UserException {

    public UsernameDuplicateException(UserErrorCode userErrorCode) {
        super(userErrorCode);
    }
}
