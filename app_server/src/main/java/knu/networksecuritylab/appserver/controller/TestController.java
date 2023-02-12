package knu.networksecuritylab.appserver.controller;

import knu.networksecuritylab.appserver.entity.user.User;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @PostMapping
    public ResponseEntity<String> testPost(@AuthenticationPrincipal User user) {
        log.info("Principal = {}", user.getName());
        return ResponseEntity.ok().body("POST 테스트");
    }

    @GetMapping
    public ResponseEntity<TestEntity> testGet(@RequestBody TestEntity testEntity) {
        log.info("TestEntity = {}", testEntity);
        return ResponseEntity.ok().body(testEntity);
    }

    @ToString
    static class TestEntity {

        private String bookName;
        private final List<String> tags = new ArrayList<>();

        public TestEntity() {
        }

        public String getBookName() {
            return bookName;
        }

        public List<String> getTags() {
            return tags;
        }
    }
}
