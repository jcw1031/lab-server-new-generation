package knu.networksecuritylab.appserver.app.repository.book;

import knu.networksecuritylab.appserver.app.entity.book.Book;
import knu.networksecuritylab.appserver.app.entity.book.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {

    List<BookTag> findByBook(Book book);
}
