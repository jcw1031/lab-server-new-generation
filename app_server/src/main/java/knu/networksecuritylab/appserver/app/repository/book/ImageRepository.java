package knu.networksecuritylab.appserver.app.repository.book;

import knu.networksecuritylab.appserver.app.entity.book.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
