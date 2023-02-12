package knu.networksecuritylab.appserver.controller.book;

import knu.networksecuritylab.appserver.controller.book.dto.BookInfoResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import knu.networksecuritylab.appserver.service.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<String> registerBook(@RequestBody @Valid final BookRegisterRequestDto bookRegisterRequestDto) {
        log.info("bookRegisterRequestDto = {}", bookRegisterRequestDto);
        Long bookId = bookService.registerBook(bookRegisterRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/books" + bookId)).body("도서 등록 완료");
    }

    @GetMapping("")
    public ResponseEntity<List<BookInfoResponseDto>> allBookList() {
        List<BookInfoResponseDto> bookList = bookService.bookList();
        return ResponseEntity.ok().body(bookList);
    }
}
