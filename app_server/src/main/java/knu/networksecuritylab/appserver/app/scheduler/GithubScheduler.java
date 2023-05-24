package knu.networksecuritylab.appserver.app.scheduler;

import knu.networksecuritylab.appserver.app.service.github.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@EnableAsync
@RequiredArgsConstructor
public class GithubScheduler {

    private final GithubService githubService;

    @Scheduled(cron = "0 */10 * * * *")
    @Async
    public void githubApi() {
        log.info("Github Repository 언어 사용량 통계 갱신 time: " + new Date());
        githubService.refreshReposLanguageInfo();
        githubService.updateLanguageRate();
    }
}
