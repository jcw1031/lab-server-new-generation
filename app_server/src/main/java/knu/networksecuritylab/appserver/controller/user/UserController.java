package knu.networksecuritylab.appserver.controller.user;

import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDTO;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDTO;
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
    public ResponseEntity<String> signUp(@Valid @RequestBody final SignUpRequestDTO signUpRequestDTO) {
        Long joinUserId = userService.join(signUpRequestDTO);
        return ResponseEntity.created(URI.create("/api/v1/users/" + joinUserId)).body("login success");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody final SignInRequestDTO signInRequestDTO) {
        String token = userService.signIn(signInRequestDTO);
        return ResponseEntity.ok().body(token);
    }
}
