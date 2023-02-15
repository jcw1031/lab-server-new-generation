package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.entity.book.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("도서 검색 - 검색어가 이름에 포함된 도서")
    void bookSearchByNameTest() throws Exception {
        //given
        Book book = Book.builder()
                .bookName("자바의 정석 1")
                .bookAuthor("남궁성")
                .build();
        Book savedBook = bookRepository.save(book);

        //when
        List<Book> bookList = bookRepository.searchBookByName("자바");

        //then
        Assertions.assertThat(bookList).contains(savedBook);
    }
}