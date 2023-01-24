package knu.networksecuritylab.appserver.entity.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponseDTO {

    private int statusCode;
    private String errorType;
    private String message;

    @Builder
    public ResponseDTO(int statusCode, String errorType, String message) {
        this.statusCode = statusCode;
        this.errorType = errorType;
        this.message = message;
    }
}
