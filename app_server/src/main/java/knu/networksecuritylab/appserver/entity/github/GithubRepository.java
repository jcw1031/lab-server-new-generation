package knu.networksecuritylab.appserver.entity.github;

import knu.networksecuritylab.appserver.api.dto.OrganizationRepositoryDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GithubRepository {

    @Id
    @Column(name = "repository_id")
    private Long id;
    private String repositoryName;
    private String repositoryDescription;
    private String pushedDate;

    @OneToMany(mappedBy = "githubRepository",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<RepositoryLanguage> repositoryLanguages = new ArrayList<>();

    @Builder
    public GithubRepository(Long id, String repositoryName, String repositoryDescription,
                            String pushedDate) {
        this.id = id;
        this.repositoryName = repositoryName;
        this.repositoryDescription = repositoryDescription;
        this.pushedDate = pushedDate;
    }

    public static GithubRepository from(final OrganizationRepositoryDto repositoryDto) {
        return GithubRepository.builder()
                .id(repositoryDto.getId())
                .repositoryName(repositoryDto.getName())
                .repositoryDescription(repositoryDto.getDescription())
                .pushedDate(repositoryDto.getPushedAt())
                .build();
    }
}
