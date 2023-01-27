package knu.networksecuritylab.appserver.entity;

import knu.networksecuritylab.appserver.entity.authority.Position;
import knu.networksecuritylab.appserver.entity.authority.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class User {

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
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;
    private String name;
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "전화번호 형식이 맞지 않습니다.")
    private String phone;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Position position = Position.GUEST;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.MEMBER;

    @Builder
    public User(Long id, String studentId, String password, String email, String name,
                String phone, Position position, Role role) {
        this.id = id;
        this.studentId = studentId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.position = position;
        this.role = role;
    }
}
