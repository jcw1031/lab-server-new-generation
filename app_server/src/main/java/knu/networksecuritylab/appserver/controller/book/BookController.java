package knu.networksecuritylab.appserver.controller.book;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import knu.networksecuritylab.appserver.service.book.BasicBookService;
import knu.networksecuritylab.appserver.service.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

}
