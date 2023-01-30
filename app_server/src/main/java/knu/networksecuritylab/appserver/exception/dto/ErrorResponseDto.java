package knu.networksecuritylab.appserver.exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponseDto {

    private int statusCode;
    private String errorType;
    private String message;

    @Builder
    public ErrorResponseDto(int statusCode, String errorType, String message) {
        this.statusCode = statusCode;
        this.errorType = errorType;
        this.message = message;
    }
}
