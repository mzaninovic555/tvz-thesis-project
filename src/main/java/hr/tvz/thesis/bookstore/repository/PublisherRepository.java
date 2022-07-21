package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
