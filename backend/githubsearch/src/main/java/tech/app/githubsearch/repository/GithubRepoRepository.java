package tech.app.githubsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.app.githubsearch.models.GithubRepository;

public interface GithubRepoRepository extends JpaRepository<GithubRepository,String> {
}
