package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.entity.book.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
