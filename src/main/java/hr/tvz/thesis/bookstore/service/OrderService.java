package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;
import java.util.List;
import java.util.Optional;

public interface OrderService {

  OrderDTO save(Order order);

  List<OrderDTO> getByUserId(Long userId);

  Optional<OrderDTO> getById(Long id);
}
