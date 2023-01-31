package knu.networksecuritylab.appserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @PostMapping
    public ResponseEntity<String> bookRegister() {
        return ResponseEntity.ok().body("테스트");
    }
}
