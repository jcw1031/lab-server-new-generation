package knu.networksecuritylab.appserver.controller.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    @NotBlank(message = "학번은 비어있을 수 없습니다.")
    @Pattern(regexp = "^\\d{9}$", message = "학번 형식이 맞지 않습니다. (9자리 정수)")
    private String studentId;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상, 20자 이하입니다..")
    private String password;

    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @NotBlank(message = "이름은 비어있을 수 없습니다.")
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "전화번호 형식이 맞지 않습니다.")
    private String phone;

    @Builder
    public SignUpRequestDto(String studentId, String password, String email, String name, String phone) {
        this.studentId = studentId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
