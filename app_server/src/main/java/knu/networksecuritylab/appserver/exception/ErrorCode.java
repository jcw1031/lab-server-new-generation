package knu.networksecuritylab.appserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    STUDENT_ID_DUPLICATE(HttpStatus.CONFLICT, "이미 가입된 학번입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 학번의 회원이 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),

    REQUEST_MESSAGE_ERROR(HttpStatus.BAD_REQUEST, "HTTP 요청 메시지를 읽을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
    }
