package knu.networksecuritylab.appserver.app.repository;

import knu.networksecuritylab.appserver.app.entity.iot.Light;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LightRepository extends JpaRepository<Light, LocalDateTime> {

    Light findFirstByOrderByCreatedAtDesc();
}
