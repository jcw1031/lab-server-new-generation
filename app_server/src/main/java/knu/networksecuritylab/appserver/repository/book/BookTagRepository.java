package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.entity.book.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {
}
