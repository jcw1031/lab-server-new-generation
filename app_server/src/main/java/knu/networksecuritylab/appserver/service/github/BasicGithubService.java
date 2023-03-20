package knu.networksecuritylab.appserver.service.github;

import knu.networksecuritylab.appserver.api.GithubApi;
import knu.networksecuritylab.appserver.controller.github.dto.OrganizationRepositoryDto;
import knu.networksecuritylab.appserver.entity.github.GithubRepository;
import knu.networksecuritylab.appserver.entity.github.RepositoryLanguage;
import knu.networksecuritylab.appserver.repository.github.GithubRepoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicGithubService implements GithubService {

    private final GithubApi githubApi;
    private final GithubRepoRepository githubRepoRepository;

    @Override
    @Transactional
    public void refreshReposLanguageInfo() {
        List<OrganizationRepositoryDto> organizationRepositories = githubApi.getOrganizationRepositories();

        for (OrganizationRepositoryDto organizationRepository : organizationRepositories) {
            GithubRepository githubRepository = GithubRepository.from(organizationRepository);
            String repositoryName = githubRepository.getRepositoryName();

            Map repositoryLanguagesMap = githubApi.getRepositoryLanguages(repositoryName);
            mappingRepositoryAndLanguages(githubRepository, repositoryLanguagesMap);

            githubRepoRepository.save(githubRepository);
        }
    }

    private List<RepositoryLanguage> mappingRepositoryAndLanguages(
            GithubRepository repository, Map languages
    ) {
        List<RepositoryLanguage> repositoryLanguages = new ArrayList<>();
        for (Object languageName : languages.keySet()) {
            Long languageBytes = Long.parseLong(String.valueOf(languages.get(languageName)));
            RepositoryLanguage repositoryLanguage =
                    RepositoryLanguage.of(String.valueOf(languageName), languageBytes);

            repositoryLanguage.setGithubRepository(repository);
            repositoryLanguages.add(repositoryLanguage);
        }
        return repositoryLanguages;
    }
}
