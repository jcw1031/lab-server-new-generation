package knu.networksecuritylab.appserver.controller.book;

import knu.networksecuritylab.appserver.controller.book.dto.BookInfoResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookListResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import knu.networksecuritylab.appserver.service.book.BookService;
import knu.networksecuritylab.appserver.service.book.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ImageService imageService;

    @PostMapping("")
    public ResponseEntity<String> registerBook(
            @RequestPart(value = "bookImages", required = false) final List<MultipartFile> bookImages,
            @RequestParam("book") final BookRegisterRequestDto bookRegisterRequestDto
    ) throws IOException {
        Long bookId = bookService.registerBook(bookImages, bookRegisterRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/books/" + bookId))
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
                .body("도서 등록 완료");
    }

    @GetMapping("")
    public ResponseEntity<List<BookListResponseDto>> randomBookListTen() {
        List<BookListResponseDto> bookList = bookService.bookList();
        return ResponseEntity.ok().body(bookList);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookInfoResponseDto> bookDetailsInfo(@PathVariable final Long bookId) {
        BookInfoResponseDto bookInfoResponseDto = bookService.bookInfo(bookId);
        return ResponseEntity.ok().body(bookInfoResponseDto);
    }

    @GetMapping(value = "/images/{imageId}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public ResponseEntity<byte[]> bookImage(@PathVariable("imageId") final Long imageId) {
        byte[] imageBytes = imageService.bookImage(imageId);
        return ResponseEntity.ok().body(imageBytes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookListResponseDto>> bookSearchList(
            @RequestParam("keyword") @NotBlank String keyword) {
        List<BookListResponseDto> bookList = bookService.bookSearch(keyword);
        return ResponseEntity.ok().body(bookList);
    }
}
