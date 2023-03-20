package knu.networksecuritylab.appserver.service.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import knu.networksecuritylab.appserver.controller.github.dto.OrganizationRepositoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicGithubService implements GithubService {

    private final ObjectMapper objectMapper;

    private RestTemplate restTemplate = new RestTemplate();
    @Value("${github.token}")
    private String token;

    @Override
    public void refreshReposLanguageInfo() {
        List<OrganizationRepositoryDto> organizationRepositories = getOrganizationRepositories();
        for (OrganizationRepositoryDto organizationRepository : organizationRepositories) {
            
        }
    }

    private List<OrganizationRepositoryDto> getOrganizationRepositories() {
        String url = "https://api.github.com/users/KNU-NetworkSecurityLab/repos";
        Header authorization = new Header("Authorization", token);

        return Arrays.asList(Objects.requireNonNull(
                restTemplate.getForObject(url, OrganizationRepositoryDto[].class, authorization))
        );
    }
}
