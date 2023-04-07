package knu.networksecuritylab.appserver.repository;

import knu.networksecuritylab.appserver.entity.iot.Light;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LightRepository extends JpaRepository<Light, LocalDateTime> {

    Light findFirstByOrderByCreatedAtDesc();
}
