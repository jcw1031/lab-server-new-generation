package knu.networksecuritylab.appserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    STUDENT_ID_DUPLICATE(HttpStatus.CONFLICT, "이미 가입된 학번입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 회원이 존재하지 않습니다."),
    INVALID_USERNAME_AND_PASSWORD(HttpStatus.UNAUTHORIZED, "학번 또는 비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
    }
