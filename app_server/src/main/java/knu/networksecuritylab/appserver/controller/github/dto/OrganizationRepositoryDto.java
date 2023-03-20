package knu.networksecuritylab.appserver.controller.github.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationRepositoryDto {

    private Long id;
    private String name;
    @JsonAlias(value = "full_name")
    private String fullName;
}
