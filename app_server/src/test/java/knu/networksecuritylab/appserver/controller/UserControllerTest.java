package knu.networksecuritylab.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDTO;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDTO;
import knu.networksecuritylab.appserver.exception.AuthException;
import knu.networksecuritylab.appserver.exception.ErrorCode;
import knu.networksecuritylab.appserver.service.UserService;
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
    UserService userService;

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void signUpSuccess() throws Exception {
        when(userService.join(any()))
                .thenReturn("sign-up success");

        mockMvc.perform(post("/api/v1/users/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(SignUpRequestDTO.builder()
                                .studentId("201901689")
                                .password("woopaca")
                                .email("jcw001031@gmail.com")
                                .phone("010-9517-1530")
                                .name("지찬우")
                                .build())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - studentId 중복")
    @WithMockUser
    void signUpFailStudentId() throws Exception {
        when(userService.join(any()))
                .thenThrow(new AuthException(ErrorCode.STUDENT_ID_DUPLICATE));

        mockMvc.perform(post("/api/v1/users/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(SignUpRequestDTO.builder()
                                .studentId("201901689")
                                .password("woopaca")
                                .email("jcw001031@gmail.com")
                                .phone("010-9517-1530")
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

        when(userService.signIn(any()))
                .thenReturn("token");

        mockMvc.perform(post("/api/v1/users/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new SignInRequestDTO(username, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 - username 없음")
    @WithMockUser
    void loginFailUsername() throws Exception {
        String studentId = "201901689";
        String password = "woopaca";

        when(userService.signIn(any()))
                .thenThrow(new AuthException(ErrorCode.USER_NOT_FOUND));

        mockMvc.perform(post("/api/v1/users/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new SignInRequestDTO(studentId, password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - password 틀림")
    @WithMockUser
    void loginFailPassword() throws Exception {
        String studentId = "201901689";
        String password = "woopaca";

        when(userService.signIn(any()))
                .thenThrow(new AuthException(ErrorCode.INVALID_PASSWORD));

        mockMvc.perform(post("/api/v1/users/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new SignInRequestDTO(studentId, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}