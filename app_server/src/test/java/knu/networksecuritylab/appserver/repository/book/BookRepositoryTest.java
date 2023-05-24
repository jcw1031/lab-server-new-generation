package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.app.entity.book.Book;
import knu.networksecuritylab.appserver.app.repository.book.BookRepository;
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
                .bookName("킹지찬우")
                .bookAuthor("지찬우")
                .build();
        Book savedBook = bookRepository.save(book);

        //when
        List<Book> bookList = bookRepository.searchBookByName("지");

        //then
        Assertions.assertThat(bookList.size()).isEqualTo(1);
    }
}