package az.company.onlinelibrarysystem.repository;

import az.company.onlinelibrarysystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    // Find authors by name (optional, useful if needed)
    List<Author> findByNameContaining(String name);

}
