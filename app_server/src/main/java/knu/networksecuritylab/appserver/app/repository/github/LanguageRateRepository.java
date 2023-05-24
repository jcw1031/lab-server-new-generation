package knu.networksecuritylab.appserver.app.repository.github;

import knu.networksecuritylab.appserver.app.entity.github.LanguageRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRateRepository extends JpaRepository<LanguageRate, String> {
}
