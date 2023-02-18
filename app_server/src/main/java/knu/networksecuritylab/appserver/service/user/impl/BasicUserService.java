package knu.networksecuritylab.appserver.service.user.impl;

import knu.networksecuritylab.appserver.config.jwt.JwtProvider;
import knu.networksecuritylab.appserver.config.jwt.JwtUtils;
import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.UserInfoResponseDto;
import knu.networksecuritylab.appserver.controller.user.dto.WithdrawalRequestDto;
import knu.networksecuritylab.appserver.entity.user.User;
import knu.networksecuritylab.appserver.exception.user.impl.InvalidAuthenticationException;
import knu.networksecuritylab.appserver.exception.user.impl.InvalidUsernameOrPassword;
import knu.networksecuritylab.appserver.exception.user.impl.UserNotFoundException;
import knu.networksecuritylab.appserver.exception.user.impl.UsernameDuplicateException;
import knu.networksecuritylab.appserver.repository.UserRepository;
import knu.networksecuritylab.appserver.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final String TOKEN_PREFIX = "Bearer ";

    @Override
    @Transactional
    public Long join(final SignUpRequestDto signUpRequestDTO) {
        User user = validateUsernameDuplicate(signUpRequestDTO);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    private User validateUsernameDuplicate(final SignUpRequestDto signUpRequestDto) {
        String studentId = signUpRequestDto.getStudentId();
        userRepository.findByStudentId(studentId)
                .ifPresent(user -> {
                    throw new UsernameDuplicateException();
                });

        return User.of(signUpRequestDto, passwordEncoder);
    }

    @Override
    @Transactional
    public String signIn(final SignInRequestDto signInRequestDto) {
        Authentication authentication = getAuthentication(signInRequestDto);

        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();

            Long authenticatedId = user.getId();
            String authenticatedStudentId = user.getUsername();
            List<String> roles = user.getRoles();

            return TOKEN_PREFIX + jwtProvider
                    .createToken(authenticatedId, authenticatedStudentId, roles, 2);
        }

        throw new InvalidAuthenticationException();
    }

    private Authentication getAuthentication(final SignInRequestDto signInRequestDto) {
        String studentId = signInRequestDto.getStudentId();
        String password = signInRequestDto.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(studentId, password);

        try {
            return authenticationManagerBuilder.getObject()
                    .authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new InvalidUsernameOrPassword();
        }
    }

    @Override
    public UserInfoResponseDto getUserInfo(final String authorization) {
        String token = jwtUtils.resolveToken(authorization);
        String studentId = jwtUtils.getStudentIdInToken(token);

        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UserNotFoundException());

        return user.toDto();
    }

    @Override
    @Transactional
    public String deleteUser(final String authorization,
                             final WithdrawalRequestDto withdrawalRequestDto) {
        String token = jwtUtils.resolveToken(authorization);
        String studentId = jwtUtils.getStudentIdInToken(token);

        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(withdrawalRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidAuthenticationException();
        }
        userRepository.delete(user);
        return "계정이 삭제되었습니다.";
    }
}
