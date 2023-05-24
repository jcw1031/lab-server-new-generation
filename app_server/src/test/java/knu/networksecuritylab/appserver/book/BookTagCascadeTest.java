package knu.networksecuritylab.appserver.book;

import knu.networksecuritylab.appserver.app.controller.book.dto.BookRegisterRequestDto;
import knu.networksecuritylab.appserver.app.entity.book.Book;
import knu.networksecuritylab.appserver.app.entity.book.BookTag;
import knu.networksecuritylab.appserver.app.repository.book.BookRepository;
import knu.networksecuritylab.appserver.app.repository.book.BookTagRepository;
import knu.networksecuritylab.appserver.app.service.book.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BookTagCascadeTest {

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookTagRepository bookTagRepository;

    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("영속성 전이 - persist")
    void bookTagCascadePersistTest() throws Exception {
        // given
        BookRegisterRequestDto bookRegisterRequestDto = BookRegisterRequestDto.builder()
                .bookName("test")
                .bookAuthor("test")
                .bookPublisher("test")
                .bookStock(2)
                .bookTagList(List.of("test1", "test2", "test3"))
                .build();

        // when
        Long bookId = bookService.registerBook(null, bookRegisterRequestDto);
        Book book = bookRepository.findById(bookId).orElse(null);
        List<BookTag> bookTags = bookTagRepository.findByBook(book);

        // then
        assertThat(bookTags.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("영속성 전이 - remove")
    void bookTagCascadeRemoveTest() throws Exception {
        // given
        BookRegisterRequestDto bookRegisterRequestDto = BookRegisterRequestDto.builder()
                .bookName("test")
                .bookAuthor("test")
                .bookPublisher("test")
                .bookStock(2)
                .bookTagList(List.of("test1", "test2", "test3"))
                .build();
        Long bookId = bookService.registerBook(null, bookRegisterRequestDto);
        Book book = bookRepository.findById(bookId).orElse(null);
        List<BookTag> bookTags = bookTagRepository.findByBook(book);
        assertThat(bookTags.size()).isEqualTo(3);

        // when
        bookService.removeBook(bookId);
        BookTag bookTag = bookTags.get(0);
        Long bookTagId = bookTag.getId();

        // then
        Assertions.assertThrows(Exception.class, () ->
                bookTagRepository.findById(bookTagId).orElseThrow(RuntimeException::new));
    }
}
