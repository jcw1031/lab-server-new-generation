package knu.networksecuritylab.appserver.controller;

import knu.networksecuritylab.appserver.controller.dto.SignInRequestDTO;
import knu.networksecuritylab.appserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {
        String token = userService.signIn(signInRequestDTO);
        return ResponseEntity.ok().body(token);
    }
}
