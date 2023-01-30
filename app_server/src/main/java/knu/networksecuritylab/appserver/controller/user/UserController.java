package knu.networksecuritylab.appserver.controller.user;

import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody final SignUpRequestDto signUpRequestDto) {
        Long joinUserId = userService.join(signUpRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/users/" + joinUserId)).body("Sign-Up Success");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody final SignInRequestDto signInRequestDto) {
        String token = userService.signIn(signInRequestDto);
        return ResponseEntity.ok().body(token);
    }
}
