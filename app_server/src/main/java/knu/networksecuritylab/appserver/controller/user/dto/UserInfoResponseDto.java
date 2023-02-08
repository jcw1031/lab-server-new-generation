package knu.networksecuritylab.appserver.controller.user.dto;

import knu.networksecuritylab.appserver.entity.user.Position;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoResponseDto {

    private String studentId;
    private String name;
    private String email;
    private String phone;
    private Position position;

    @Builder
    public UserInfoResponseDto(String studentId, String name, String email, String phone, Position position) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.position = position;
    }
}
