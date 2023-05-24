package knu.networksecuritylab.appserver.app.repository.book;

import knu.networksecuritylab.appserver.app.entity.book.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);
}
