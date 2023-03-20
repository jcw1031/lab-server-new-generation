package knu.networksecuritylab.appserver.controller.github.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationRepositoryDto {

    private Long id;
    private String name;
    private String description;
}