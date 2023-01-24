package knu.networksecuritylab.appserver.service;

import knu.networksecuritylab.appserver.controller.dto.SignInRequestDTO;
import knu.networksecuritylab.appserver.controller.dto.SignUpRequestDTO;
import knu.networksecuritylab.appserver.entity.User;
import knu.networksecuritylab.appserver.exception.AuthException;
import knu.networksecuritylab.appserver.exception.ErrorCode;
import knu.networksecuritylab.appserver.jwt.JwtUtil;
import knu.networksecuritylab.appserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final SecretKey secretKey;

    public String join(final SignUpRequestDTO signUpRequestDTO) {
        return "";
    }

    public String signIn(final SignInRequestDTO signInRequestDTO) {
        System.out.println("UserService.signIn");
        User user = userRepository.findByStudentId(signInRequestDTO.getStudentId())
                .orElseThrow(() -> new AuthException(ErrorCode.USER_NOT_FOUND));

        if (!encoder.matches(signInRequestDTO.getPassword(), user.getPassword())) { // 순서 주의
            throw new AuthException(ErrorCode.INVALID_PASSWORD);
        }

        return JwtUtil.createToken(user.getId(), user.getStudentId(), secretKey);
    }
}
