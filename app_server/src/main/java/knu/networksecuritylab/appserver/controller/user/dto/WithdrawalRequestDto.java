package knu.networksecuritylab.appserver.controller.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class WithdrawalRequestDto {

    @NotBlank(message = "항목이 비어있을 수 없습니다.")
    private String password;
}
