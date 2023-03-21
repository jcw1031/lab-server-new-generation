package knu.networksecuritylab.appserver;

import knu.networksecuritylab.appserver.controller.github.dto.OrganizationRepositoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateTest {

    @Test
    void restTemplateTest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OrganizationRepositoryDto[]> responseEntity = restTemplate
                .getForEntity(
                        "https://api.github.com/users/KNU-NetworkSecurityLab/repos",
                        OrganizationRepositoryDto[].class,
                        new Header("Authorization", "94fc3617b1539ebed9040efcf4c90ccb8d193cb2")
                );
        String name = responseEntity.getBody()[0].getName();
        System.out.println("name = " + name);
    }

    @Test
    void repositoryLanguagesTest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> responseEntity = restTemplate
                .getForEntity(
                        "https://api.github.com/repos/KNU-NetworkSecurityLab/BoT/languages",
                        Map.class,
                        new Header("Authorization", "94fc3617b1539ebed9040efcf4c90ccb8d193cb2")
                );

        Map<String, Long> body = Map.copyOf(responseEntity.getBody());
        for (String key : body.keySet()) {
            System.out.println("key: " + key + ", value: " + body.get(key));
        }
    }
}
