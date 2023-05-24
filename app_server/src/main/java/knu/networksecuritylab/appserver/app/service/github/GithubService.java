package knu.networksecuritylab.appserver.app.service.github;

import knu.networksecuritylab.appserver.app.controller.github.dto.LanguageRateResponseDto;
import knu.networksecuritylab.appserver.app.controller.github.dto.RepositoryListResponseDto;

import java.util.List;

public interface GithubService {

    void refreshReposLanguageInfo();

    void updateLanguageRate();

    List<LanguageRateResponseDto> organizationLanguagesRate();

    List<RepositoryListResponseDto> organizationRepositoryList();
}
