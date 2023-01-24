package knu.networksecuritylab.appserver.service;

import knu.networksecuritylab.appserver.controller.dto.SignInRequestDTO;
import knu.networksecuritylab.appserver.controller.dto.SignUpRequestDTO;
import knu.networksecuritylab.appserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(final SignUpRequestDTO signUpRequestDTO) {
        return "";
    }

    public String signIn(final SignInRequestDTO signInRequestDTO) {
        return "";
    }
}
