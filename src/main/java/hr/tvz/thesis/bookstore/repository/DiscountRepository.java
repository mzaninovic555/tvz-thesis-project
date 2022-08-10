package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
