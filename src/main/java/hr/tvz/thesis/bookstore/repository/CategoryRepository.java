package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findById(Long id);
}
