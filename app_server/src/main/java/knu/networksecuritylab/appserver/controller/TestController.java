package knu.networksecuritylab.appserver.controller;

import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @PostMapping
    public ResponseEntity<String> bookRegister(@Valid @RequestBody BookRegisterRequestDTO bookRegisterRequestDTO) {
        System.out.println("bookRegisterRequestDTO = " + bookRegisterRequestDTO);
        return ResponseEntity.ok().body("책 등록 완료");
    }
}
