package tech.app.githubsearch.repository;
import tech.app.githubsearch.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.app.githubsearch.models.Person;

public interface UserRepository extends JpaRepository<Person,Long> {
}
