package knu.networksecuritylab.appserver.app.api;

import knu.networksecuritylab.appserver.app.api.dto.OrganizationRepositoryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class GithubApi {

    private RestTemplate restTemplate = new RestTemplate();
    @Value("${github.token}")
    private String token;

    public List<OrganizationRepositoryDto> getOrganizationRepositories() {
        String url = "https://api.github.com/users/KNU-NetworkSecurityLab/repos";
        Header authorization = new Header("Authorization", token);

        return Arrays.asList(Objects.requireNonNull(
                restTemplate.getForObject(url, OrganizationRepositoryDto[].class, authorization))
        );
    }

    public Map getRepositoryLanguages(String repositoryName) {
        String url = "https://api.github.com/repos/KNU-NetworkSecurityLab/" + repositoryName + "/languages";
        Header authorization = new Header("Authorization", token);

        return restTemplate.getForObject(url, Map.class, authorization);
    }
}
