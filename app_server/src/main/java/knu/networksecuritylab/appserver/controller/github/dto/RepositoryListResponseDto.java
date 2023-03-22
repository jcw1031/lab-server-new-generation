package knu.networksecuritylab.appserver.controller.github.dto;

import knu.networksecuritylab.appserver.entity.github.GithubRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RepositoryListResponseDto {

    private Long repositoryId;
    private String repositoryName;

    @Builder
    public RepositoryListResponseDto(Long repositoryId, String repositoryName) {
        this.repositoryId = repositoryId;
        this.repositoryName = repositoryName;
    }

    public static RepositoryListResponseDto from(final GithubRepository githubRepository) {
        return RepositoryListResponseDto.builder()
                .repositoryId(githubRepository.getId())
                .repositoryName(githubRepository.getRepositoryName())
                .build();
    }
}
