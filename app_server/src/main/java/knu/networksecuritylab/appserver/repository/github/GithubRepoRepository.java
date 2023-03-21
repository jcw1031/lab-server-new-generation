package knu.networksecuritylab.appserver.repository.github;

import knu.networksecuritylab.appserver.entity.github.GithubRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubRepoRepository extends JpaRepository<GithubRepository, Long> {
}
