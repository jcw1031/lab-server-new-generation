package knu.networksecuritylab.appserver.entity;

import knu.networksecuritylab.appserver.entity.authority.Position;
import knu.networksecuritylab.appserver.entity.authority.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "학번은 비어있을 수 없습니다.")
    private String studentId;
    @NotEmpty(message = "비밀번호는 비어있을 수 없습니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상, 20자 이하입니다..")
    private String password;
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;
    private String name;
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "전화번호 형식이 맞지 않습니다.")
    private String phone;
    @Builder.Default
    private Position position = Position.GUEST;
    @Builder.Default
    private Role role = Role.MEMBER;

    @Builder
    public User(Long id, String studentId, String password, String email, String name, String phone, Position position, Role role) {
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
