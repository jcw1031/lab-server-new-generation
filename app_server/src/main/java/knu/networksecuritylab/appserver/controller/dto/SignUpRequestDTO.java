package knu.networksecuritylab.appserver.controller.dto;

import knu.networksecuritylab.appserver.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDTO {

    @Column(unique = true)
    @NotEmpty(message = "학번은 비어있을 수 없습니다.")
    private String studentId;
    @NotEmpty(message = "비밀번호는 비어있을 수 없습니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상, 20자 이하입니다..")
    private String password;
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;
    private String name;
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "전화번호 형식이 맞지 않습니다.")
    private String phone;

    public User toEntity(BCryptPasswordEncoder encoder) {
        return User.builder()
                .studentId(studentId)
                .password(encoder.encode(password))
                .email(email)
                .name(name)
                .phone(phone)
                .build();
    }
}
