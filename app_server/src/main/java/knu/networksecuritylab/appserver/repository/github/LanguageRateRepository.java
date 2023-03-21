package knu.networksecuritylab.appserver.repository.github;

import knu.networksecuritylab.appserver.entity.github.LanguageRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRateRepository extends JpaRepository<LanguageRate, String> {
}
