package knu.networksecuritylab.appserver.service.github;

import knu.networksecuritylab.appserver.api.GithubApi;
import knu.networksecuritylab.appserver.repository.github.GithubRepoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicGithubServiceTest {

    @Autowired
    GithubApi githubApi;
    @Autowired
    GithubRepoRepository githubRepoRepository;
    @Autowired
    GithubService githubService;

    @Test
    void organizationRepositorySaveTest() throws Exception {
        githubService.refreshReposLanguageInfo();
    }
}