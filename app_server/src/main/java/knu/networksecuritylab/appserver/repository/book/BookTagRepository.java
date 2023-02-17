package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.entity.book.Book;
import knu.networksecuritylab.appserver.entity.book.BookTag;
import knu.networksecuritylab.appserver.entity.book.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {

    Optional<BookTag> findByBookAndTag(final Book book, final Tag tag);
}
