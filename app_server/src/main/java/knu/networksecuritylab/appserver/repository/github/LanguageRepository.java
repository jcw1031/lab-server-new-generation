package knu.networksecuritylab.appserver.repository.github;

import knu.networksecuritylab.appserver.entity.github.RepositoryLanguage;
import knu.networksecuritylab.appserver.repository.github.dto.LanguageRateInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<RepositoryLanguage, Long> {

    @Query(value = "SELECT language_name AS name, " +
            "(SUM(repository_language_bytes) / (SELECT SUM(repository_language_bytes) FROM repository_language) * 100) AS rate " +
            "FROM lab_service.repository_language " +
            "GROUP BY language_name ", nativeQuery = true)
    List<LanguageRateInterface> calculateLanguageRate();
}
