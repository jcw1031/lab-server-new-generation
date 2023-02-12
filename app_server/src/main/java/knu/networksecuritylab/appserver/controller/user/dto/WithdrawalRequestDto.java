package knu.networksecuritylab.appserver.controller.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WithdrawalRequestDto {

    @NotBlank(message = "항목이 비어있을 수 없습니다.")
    private String password;
}
