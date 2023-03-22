package knu.networksecuritylab.appserver.entity.github;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RepositoryLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repo_language_id")
    private Long id;
    private String languageName;
    private Long repositoryLanguageBytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_id")
    private GithubRepository githubRepository;

    @Builder
    public RepositoryLanguage(String languageName, Long repositoryLanguageBytes) {
        this.languageName = languageName;
        this.repositoryLanguageBytes = repositoryLanguageBytes;
    }

    public static RepositoryLanguage of(String languageName, Long repositoryLanguageBytes) {
        return RepositoryLanguage.builder()
                .languageName(languageName)
                .repositoryLanguageBytes(repositoryLanguageBytes)
                .build();
    }

    public void setGithubRepository(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
        githubRepository.getRepositoryLanguages().add(this);
    }
}
