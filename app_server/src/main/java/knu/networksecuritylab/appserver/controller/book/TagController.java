package knu.networksecuritylab.appserver.controller.book;

import knu.networksecuritylab.appserver.service.book.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<String>> getAllTagList() {
        List<String> tagList = tagService.tagList();
        return ResponseEntity.ok().body(tagList);
    }
}
