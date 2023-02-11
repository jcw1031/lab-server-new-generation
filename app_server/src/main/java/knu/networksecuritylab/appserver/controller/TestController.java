package knu.networksecuritylab.appserver.controller;

import knu.networksecuritylab.appserver.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @PostMapping
    public ResponseEntity<String> testPost(@AuthenticationPrincipal User user) {
        log.info("Principal = {}", user.getStudentId());
        return ResponseEntity.ok().body("POST 테스트");
    }

    @GetMapping
    public ResponseEntity<String> testGet() {
        return ResponseEntity.ok().body("GET 테스트");
    }
}
