package knu.networksecuritylab.appserver.service;

import knu.networksecuritylab.appserver.config.jwt.JwtProvider;
import knu.networksecuritylab.appserver.config.jwt.JwtUtils;
import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.UserInfoResponseDto;
import knu.networksecuritylab.appserver.controller.user.dto.WithdrawalRequestDto;
import knu.networksecuritylab.appserver.entity.user.User;
import knu.networksecuritylab.appserver.exception.CustomAuthException;
import knu.networksecuritylab.appserver.exception.ErrorCode;
import knu.networksecuritylab.appserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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

    /*public String signIn(final SignInRequestDto signInRequestDTO) {
        User user = usernameAndPasswordValidate(signInRequestDTO);

        String token = jwtProvider.createToken(user.getId(), user.getStudentId(), user.getRoles());
        return TOKEN_PREFIX + token;
    }

    private User usernameAndPasswordValidate(final SignInRequestDto signInRequestDto) {
        User user = userRepository.findByStudentId(signInRequestDto.getStudentId())
                .orElseThrow(() -> new CustomAuthException(ErrorCode.INVALID_USERNAME_OR_PASSWORD));

        if (!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            throw new CustomAuthException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        return user;
    }*/

    public String signIn(final SignInRequestDto signInRequestDto) {
        String studentId = signInRequestDto.getStudentId();
        String password = signInRequestDto.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(studentId, password);

        Authentication authentication;
        try {
            authentication = authenticationManagerBuilder.getObject()
                    .authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new CustomAuthException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();

            Long authenticatedId = user.getId();
            String authenticatedStudentId = user.getUsername();
            List<String> roles = user.getRoles();

            return TOKEN_PREFIX + jwtProvider.createToken(authenticatedId, authenticatedStudentId, roles);
        }

        throw new CustomAuthException(ErrorCode.INVALID_AUTHORIZATION);
    }

    public UserInfoResponseDto getUserInfo(final String authorization) {
        String token = jwtUtils.resolveToken(authorization);
        String studentId = jwtUtils.getStudentIdInToken(token);

        User user = userRepository.findByStudentId(studentId).orElseThrow(() ->
                new CustomAuthException(ErrorCode.USER_NOT_FOUND));

        return user.toDto();
    }

    public String deleteUser(final String authorization, final WithdrawalRequestDto withdrawalRequestDto) {
        String token = jwtUtils.resolveToken(authorization);
        String studentId = jwtUtils.getStudentIdInToken(token);

        User user = userRepository.findByStudentId(studentId).orElseThrow(() ->
                new CustomAuthException(ErrorCode.USER_NOT_FOUND));

        if (!withdrawalRequestDto.getPassword().equals(user.getPassword())) {
            throw new CustomAuthException(ErrorCode.INVALID_AUTHORIZATION);
        }
        userRepository.delete(user);
        return "계정이 삭제되었습니다.";
    }
}
