package knu.networksecuritylab.appserver.service.github;

import knu.networksecuritylab.appserver.api.GithubApi;
import knu.networksecuritylab.appserver.controller.github.dto.OrganizationRepositoryDto;
import knu.networksecuritylab.appserver.entity.github.GithubRepository;
import knu.networksecuritylab.appserver.entity.github.LanguageRate;
import knu.networksecuritylab.appserver.entity.github.RepositoryLanguage;
import knu.networksecuritylab.appserver.repository.github.GithubRepoRepository;
import knu.networksecuritylab.appserver.repository.github.LanguageRateRepository;
import knu.networksecuritylab.appserver.repository.github.LanguageRepository;
import knu.networksecuritylab.appserver.repository.github.dto.LanguageRateInterface;
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
    private final LanguageRateRepository languageRateRepository;

    @Override
    @Transactional
    public void refreshReposLanguageInfo() {
        languageRepository.deleteAll();
        List<OrganizationRepositoryDto> organizationRepositories = githubApi.getOrganizationRepositories();

        for (OrganizationRepositoryDto organizationRepository : organizationRepositories) {
            GithubRepository githubRepository = GithubRepository.from(organizationRepository);
            String repositoryName = githubRepository.getRepositoryName();

            githubRepoRepository.save(githubRepository);

            Map repositoryLanguagesMap = githubApi.getRepositoryLanguages(repositoryName);
            mappingRepositoryAndLanguages(githubRepository, repositoryLanguagesMap);
        }
    }

    @Override
    @Transactional
    public void updateLanguageRate() {
        List<LanguageRateInterface> languageRateInterfaces = languageRepository.calculateLanguageRate();

        for (LanguageRateInterface languageRateInterface : languageRateInterfaces) {
            log.info("{}", languageRateInterface.getClass());
            languageRateRepository.save(LanguageRate.from(languageRateInterface));
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
