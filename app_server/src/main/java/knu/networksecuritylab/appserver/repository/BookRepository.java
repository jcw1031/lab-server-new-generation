package knu.networksecuritylab.appserver.repository;

import knu.networksecuritylab.appserver.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
