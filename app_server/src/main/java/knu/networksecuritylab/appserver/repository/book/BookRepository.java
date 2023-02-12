package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookName(String bookName);

    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %:searchWord%")
    List<Book> searchBookName(@Param("searchWord") String searchWord);

    @Query("SELECT b FROM Book b WHERE b.bookAuthor LIKE %:searchWord%")
    List<Book> searchBookAuthor(@Param("searchWord") String searchWord);
}
