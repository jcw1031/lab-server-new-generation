package knu.networksecuritylab.appserver.service;

import knu.networksecuritylab.appserver.config.jwt.JwtProvider;
import knu.networksecuritylab.appserver.config.jwt.JwtUtils;
import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.UserInfoResponseDto;
import knu.networksecuritylab.appserver.entity.user.User;
import knu.networksecuritylab.appserver.exception.CustomAuthException;
import knu.networksecuritylab.appserver.exception.ErrorCode;
import knu.networksecuritylab.appserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;

    private final String TOKEN_PREFIX = "Bearer ";

    public Long join(final SignUpRequestDto signUpRequestDTO) {
        User user = checkUsernameDuplicate(signUpRequestDTO);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    private User checkUsernameDuplicate(final SignUpRequestDto signUpRequestDto) {
        String studentId = signUpRequestDto.getStudentId();
        userRepository.findByStudentId(studentId)
                .ifPresent(user -> {
                    throw new CustomAuthException(ErrorCode.STUDENT_ID_DUPLICATE);
                });

        return User.of(signUpRequestDto, passwordEncoder);
    }

    public String signIn(final SignInRequestDto signInRequestDTO) {
        User user = usernameAndPasswordValidate(signInRequestDTO);

        String token = jwtProvider.createToken(user.getId(), user.getStudentId(), user.getRoles());
        return TOKEN_PREFIX + token;
    }

    private User usernameAndPasswordValidate(final SignInRequestDto signInRequestDto) {
        User user = userRepository.findByStudentId(signInRequestDto.getStudentId())
                .orElseThrow(() -> new CustomAuthException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            throw new CustomAuthException(ErrorCode.INVALID_PASSWORD);
        }

        return user;
    }

    public UserInfoResponseDto getUserInfo(HttpServletRequest request) {
        String token = jwtUtils.resolveToken(request);
        String studentId = jwtUtils.getStudentId(token);

        User user = userRepository.findByStudentId(studentId).orElseThrow(() ->
                new CustomAuthException(ErrorCode.USER_NOT_FOUND));

        return user.toDto();
    }
}
