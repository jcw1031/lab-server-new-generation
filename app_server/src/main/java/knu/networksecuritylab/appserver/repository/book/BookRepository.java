package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.entity.book.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookName(String bookName);

    @Query("SELECT b FROM Book b JOIN FETCH b.images WHERE b.id = :bookId")
    Optional<Book> findByIdIfImagesExists(@Param("bookId") Long id);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookTags bt" +
            " LEFT JOIN FETCH bt.tag WHERE b.id = :bookId")
    Optional<Book> findByIdWithBookTags(@Param("bookId") Long id);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookTags bt LEFT JOIN FETCH bt.tag ORDER BY RAND()")
    List<Book> findBookRandomList(Pageable pageable);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookTags bt LEFT JOIN FETCH bt.tag WHERE b.bookName LIKE %:keyword%")
    List<Book> searchBookByName(Pageable pageable, @Param("keyword") String keyword);
}
