package knu.networksecuritylab.appserver.controller.user;

import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.UserInfoResponseDto;
import knu.networksecuritylab.appserver.controller.user.dto.WithdrawalRequestDto;
import knu.networksecuritylab.appserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid final SignUpRequestDto signUpRequestDto) {
        Long joinUserId = userService.join(signUpRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/users/" + joinUserId)).body("Sign-Up Success");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody @Valid final SignInRequestDto signInRequestDto) {
        String token = userService.signIn(signInRequestDto);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("")
    public ResponseEntity<UserInfoResponseDto> userInfo(final @RequestHeader("Authorization") String authorization) {
        UserInfoResponseDto userInfoResponseDto = userService.getUserInfo(authorization);
        return ResponseEntity.ok().body(userInfoResponseDto);
    }

    @DeleteMapping("")
    public ResponseEntity<String> withdrawalMembership(final @RequestHeader("Authorization") String authorization,
                                                       final @RequestBody WithdrawalRequestDto withdrawalRequestDto) {
        String result = userService.deleteUser(authorization, withdrawalRequestDto);
        return ResponseEntity.ok().body(result);
    }
}
