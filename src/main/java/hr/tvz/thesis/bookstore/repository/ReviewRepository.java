package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
