package knu.networksecuritylab.appserver.app.exception.user.impl;

import knu.networksecuritylab.appserver.app.exception.user.UserErrorCode;
import knu.networksecuritylab.appserver.app.exception.user.UserException;

public class UsernameDuplicateException extends UserException {

    public UsernameDuplicateException() {
        super(UserErrorCode.STUDENT_ID_DUPLICATE);
    }
}
