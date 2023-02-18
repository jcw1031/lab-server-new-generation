package knu.networksecuritylab.appserver.entity.user;

import knu.networksecuritylab.appserver.controller.user.dto.SignUpRequestDto;
import knu.networksecuritylab.appserver.controller.user.dto.UserInfoResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "학번은 비어있을 수 없습니다.")
    @Pattern(regexp = "^\\d{9}$", message = "학번 형식이 맞지 않습니다. (9자리 정수)")
    private String studentId;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    private String password;

    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @NotBlank(message = "이름은 비어있을 수 없습니다.")
    private String name;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Position position = Position.MEMBER;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Builder
    public User(Long id, String studentId, String password, String email, String name,
                Position position, List<String> roles) {
        this.id = id;
        this.studentId = studentId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.position = position;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.studentId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static User of(SignUpRequestDto signUpRequestDto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .studentId(signUpRequestDto.getStudentId())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }

    public UserInfoResponseDto toDto() {
        return UserInfoResponseDto.builder()
                .studentId(this.studentId)
                .name(this.name)
                .email(this.email)
                .position(this.position)
                .build();
    }
}
