package knu.networksecuritylab.appserver.service;

import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.entity.User;
import knu.networksecuritylab.appserver.exception.custom.CustomAuthException;
import knu.networksecuritylab.appserver.exception.ErrorCode;
import knu.networksecuritylab.appserver.config.jwt.JwtProvider;
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

    public Long join(final SignUpRequestDto signUpRequestDTO) {
        userRepository.findByStudentId(signUpRequestDTO.getStudentId())
                .ifPresent(user -> {
                    throw new CustomAuthException(ErrorCode.STUDENT_ID_DUPLICATE);
                });

        User user = signUpRequestDTO.toEntity(encoder);
        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }

    public String signIn(final SignInRequestDto signInRequestDTO) {
        User user = userRepository.findByStudentId(signInRequestDTO.getStudentId())
                .orElseThrow(() -> new CustomAuthException(ErrorCode.USER_NOT_FOUND));

        if (!encoder.matches(signInRequestDTO.getPassword(), user.getPassword())) { // 순서 주의
            throw new CustomAuthException(ErrorCode.INVALID_PASSWORD);
        }

        return JwtProvider.createToken(user.getId(), user.getStudentId(), user.getRole(), secretKey);
    }
}
