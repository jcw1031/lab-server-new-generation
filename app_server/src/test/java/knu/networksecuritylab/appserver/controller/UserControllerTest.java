package knu.networksecuritylab.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import knu.networksecuritylab.appserver.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("회원가입 성공")
//    @WithMockUser // 테스트에 필요한 인증된 인증 정보를 제공한다.
    @WithAnonymousUser
    @Rollback(value = false)
    void signUpSuccess() throws Exception {
        mockMvc.perform(post("/api/v1/users/sign-up")
//                        .with(csrf())
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
//    @WithMockUser
    @WithAnonymousUser
    void signInSuccess() throws Exception {
        String studentId = "201901689";
        String password = "woopaca";

        mockMvc.perform(post("/api/v1/users/sign-in")
//                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new SignInRequestDto(studentId, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 - username 없음")
    @WithMockUser
    void loginFailUsername() throws Exception {
        String studentId = "201901689";
        String password = "woopaca";

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

        mockMvc.perform(post("/api/v1/users/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new SignInRequestDto(studentId, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}