package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> getByUserId(Long userId);
}
