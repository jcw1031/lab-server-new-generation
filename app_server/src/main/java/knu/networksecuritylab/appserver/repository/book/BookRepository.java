package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookName(String bookName);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookTags bt LEFT JOIN FETCH bt.tag WHERE b.id = :bookId")
    Optional<Book> findByIdFetchJoin(@Param("bookId") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM Book ORDER BY RAND() LIMIT 10")
    List<Book> findBookRandomList();

    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %:keyword%")
    List<Book> searchBookByName(@Param("keyword") String keyword);
}
