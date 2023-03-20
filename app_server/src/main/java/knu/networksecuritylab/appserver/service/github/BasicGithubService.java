package knu.networksecuritylab.appserver.service.github;

import knu.networksecuritylab.appserver.api.GithubApi;
import knu.networksecuritylab.appserver.controller.github.dto.OrganizationRepositoryDto;
import knu.networksecuritylab.appserver.entity.github.GithubRepository;
import knu.networksecuritylab.appserver.entity.github.RepositoryLanguage;
import knu.networksecuritylab.appserver.repository.github.GithubRepoRepository;
import knu.networksecuritylab.appserver.repository.github.LanguageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BasicGithubService implements GithubService {

    private final GithubApi githubApi;
    private final GithubRepoRepository githubRepoRepository;
    private final LanguageRepository languageRepository;

    @Override
    @Transactional
    public void refreshReposLanguageInfo() {
        languageRepository.deleteAll();
        List<OrganizationRepositoryDto> organizationRepositories = githubApi.getOrganizationRepositories();

        for (OrganizationRepositoryDto organizationRepository : organizationRepositories) {
            GithubRepository githubRepository = GithubRepository.from(organizationRepository);
            String repositoryName = githubRepository.getRepositoryName();

            Map repositoryLanguagesMap = githubApi.getRepositoryLanguages(repositoryName);
            mappingRepositoryAndLanguages(githubRepository, repositoryLanguagesMap);

            githubRepoRepository.save(githubRepository);
        }
    }

    private void mappingRepositoryAndLanguages(
            GithubRepository repository, Map languages
    ) {
        for (Object languageName : languages.keySet()) {
            Long languageBytes = Long.parseLong(String.valueOf(languages.get(languageName)));
            RepositoryLanguage repositoryLanguage =
                    RepositoryLanguage.of(String.valueOf(languageName), languageBytes);

            repositoryLanguage.setGithubRepository(repository);
            languageRepository.save(repositoryLanguage);
        }
    }
}
