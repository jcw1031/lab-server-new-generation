package knu.networksecuritylab.appserver.app.repository.github;

import knu.networksecuritylab.appserver.app.entity.github.GithubRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubRepoRepository extends JpaRepository<GithubRepository, Long> {
}
