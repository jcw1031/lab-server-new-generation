package knu.networksecuritylab.appserver.mapping;

import knu.networksecuritylab.appserver.entity.book.Book;
import knu.networksecuritylab.appserver.entity.book.BookTag;
import knu.networksecuritylab.appserver.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class MappingTest {

    @Autowired
    private BookRepository bookRepository;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("매핑 테스트")
    void mappingTest() throws Exception {
        //given

        //when
        Book book = bookRepository.findById(1L).orElse(null);

        //then
        List<BookTag> bookTags = book.getBookTags();
        Assertions.assertThat(bookTags.size()).isEqualTo(2);
    }
}
