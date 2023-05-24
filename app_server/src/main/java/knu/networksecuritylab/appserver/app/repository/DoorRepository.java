package knu.networksecuritylab.appserver.app.repository;

import knu.networksecuritylab.appserver.app.entity.iot.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoorRepository extends JpaRepository<Door, LocalDateTime> {

    Door findFirstByOrderByCreatedAtDesc();
}
