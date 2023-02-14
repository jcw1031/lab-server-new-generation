package knu.networksecuritylab.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import knu.networksecuritylab.appserver.config.jwt.JwtAuthenticationFilter;
import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.exception.CustomAuthException;
import knu.networksecuritylab.appserver.exception.UserErrorCode;
import knu.networksecuritylab.appserver.service.user.BasicUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    BasicUserService basicUserService;
    @MockBean
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void signUpSuccess() throws Exception {
        when(basicUserService.join(any()))
                .thenReturn(1L);

        mockMvc.perform(post("/api/v1/users/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(SignUpRequestDto.builder()
                                .studentId("201901689")
                                .password("woopaca")
                                .email("jcw001031@gmail.com")
                                .name("지찬우")
                                .build())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - studentId 중복")
    @WithMockUser
    void signUpFailStudentId() throws Exception {
        when(basicUserService.join(any()))
                .thenThrow(new CustomAuthException(UserErrorCode.STUDENT_ID_DUPLICATE));

        mockMvc.perform(post("/api/v1/users/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(SignUpRequestDto.builder()
                                .studentId("201901689")
                                .password("woopaca")
                                .email("jcw001031@gmail.com")
                                .name("지찬우")
                                .build())))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void signInSuccess() throws Exception {
        String username = "woopaca";
        String password = "woopaca";

        when(basicUserService.signIn(any()))
                .thenReturn("token");

        mockMvc.perform(post("/api/v1/users/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new SignInRequestDto(username, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 - username 없음")
    @WithMockUser
    void loginFailUsername() throws Exception {
        String studentId = "201901689";
        String password = "woopaca";

        when(basicUserService.signIn(any()))
                .thenThrow(new CustomAuthException(UserErrorCode.USER_NOT_FOUND));

        mockMvc.perform(post("/api/v1/users/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new SignInRequestDto(studentId, password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - password 틀림")
    @WithMockUser
    void loginFailPassword() throws Exception {
        String studentId = "201901689";
        String password = "woopaca";

        when(basicUserService.signIn(any()))
                .thenThrow(new CustomAuthException(UserErrorCode.INVALID_USERNAME_OR_PASSWORD));

        mockMvc.perform(post("/api/v1/users/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new SignInRequestDto(studentId, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}