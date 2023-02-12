package knu.networksecuritylab.appserver.exception.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponseDto {

    private int statusCode;
    private String errorType;
    private final List<String> messages = new ArrayList<>();
    private String path;

    @Builder
    public ErrorResponseDto(int statusCode, String errorType, String path) {
        this.statusCode = statusCode;
        this.errorType = errorType;
        this.path = path;
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
