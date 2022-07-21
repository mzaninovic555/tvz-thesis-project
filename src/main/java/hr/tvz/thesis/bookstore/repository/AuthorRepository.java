package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
