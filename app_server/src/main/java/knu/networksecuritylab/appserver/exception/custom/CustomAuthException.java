package knu.networksecuritylab.appserver.exception.custom;

import knu.networksecuritylab.appserver.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomAuthException extends RuntimeException {

    private ErrorCode errorCode;
}
