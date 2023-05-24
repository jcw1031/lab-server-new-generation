package knu.networksecuritylab.appserver.app.controller.github;

import knu.networksecuritylab.appserver.app.controller.github.dto.LanguageRateResponseDto;
import knu.networksecuritylab.appserver.app.controller.github.dto.RepositoryListResponseDto;
import knu.networksecuritylab.appserver.app.service.github.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/github")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/organization/languages")
    public ResponseEntity<List<LanguageRateResponseDto>> githubOrganizationLanguagesRateInfo() {
        List<LanguageRateResponseDto> languageRateList = githubService.organizationLanguagesRate();
        return ResponseEntity.ok().body(languageRateList);
    }

    @GetMapping("/repositories")
    public ResponseEntity<List<RepositoryListResponseDto>> githubRepositoryListInfo() {
        List<RepositoryListResponseDto> repositoryList = githubService.organizationRepositoryList();
        return ResponseEntity.ok().body(repositoryList);
    }
}
