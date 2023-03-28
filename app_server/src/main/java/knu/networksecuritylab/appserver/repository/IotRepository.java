package knu.networksecuritylab.appserver.repository;

import knu.networksecuritylab.appserver.entity.iot.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IotRepository extends JpaRepository<Door, LocalDateTime> {

    Door findFirstByOrderByCreatedAtDesc();
}
