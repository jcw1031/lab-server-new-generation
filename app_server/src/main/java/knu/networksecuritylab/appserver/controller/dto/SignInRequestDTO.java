package knu.networksecuritylab.appserver.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignInRequestDTO {

    @Column(unique = true)
    @NotEmpty(message = "학번은 비어있을 수 없습니다.")
    private String studentId;
    @NotEmpty(message = "비밀번호는 비어있을 수 없습니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상, 20자 이하입니다..")
    private String password;
}
