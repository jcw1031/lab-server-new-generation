package knu.networksecuritylab.appserver.entity.github;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GithubRepository {

    @Id
    @Column(name = "repository_id")
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "githubRepository")
    private List<RepositoryLanguage> repositoryLanguages;

    @Builder
    public GithubRepository(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
