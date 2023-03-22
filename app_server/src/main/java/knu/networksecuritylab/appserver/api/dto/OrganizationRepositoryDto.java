package knu.networksecuritylab.appserver.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationRepositoryDto {

    private Long id;
    private String name;
    private String description;
    @JsonAlias(value = "updated_at")
    private String updateAt;
}
