package knu.networksecuritylab.appserver.controller.github;

import knu.networksecuritylab.appserver.controller.github.dto.LanguageRateResponseDto;
import knu.networksecuritylab.appserver.service.github.GithubService;
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
}
