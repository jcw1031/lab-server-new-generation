package knu.networksecuritylab.appserver.app.exception.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FileErrorCode {

    CAN_NOT_CREATE_DIRECTORY(HttpStatus.INTERNAL_SERVER_ERROR, "폴더를 만들 수 없습니다."),
    INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "파일 확장자명이 올바르지 않습니다."),
    IMAGE_NOT_READABLE(HttpStatus.INTERNAL_SERVER_ERROR, "이미지를 읽을 수 없습니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
