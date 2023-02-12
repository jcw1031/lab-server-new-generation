package knu.networksecuritylab.appserver.service.user;

import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.UserInfoResponseDto;
import knu.networksecuritylab.appserver.controller.user.dto.WithdrawalRequestDto;

public interface UserService {

    Long join(final SignUpRequestDto signUpRequestDto);

    String signIn(final SignInRequestDto signInRequestDto);

    UserInfoResponseDto getUserInfo(final String auth);

    String deleteUser(final String auth, final WithdrawalRequestDto withdrawalRequestDto);
}
