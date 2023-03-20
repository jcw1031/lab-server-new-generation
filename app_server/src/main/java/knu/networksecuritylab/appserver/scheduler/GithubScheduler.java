package knu.networksecuritylab.appserver.scheduler;

import knu.networksecuritylab.appserver.service.github.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableAsync
@RequiredArgsConstructor
public class GithubScheduler {

    private final GithubService githubService;

    @Scheduled(cron = "0 0 0 * * *")
    @Async
    public void githubApi() {
    }
}
