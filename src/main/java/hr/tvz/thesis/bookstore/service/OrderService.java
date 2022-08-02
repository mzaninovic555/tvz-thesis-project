package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;

public interface OrderService {

  OrderDTO save(Order order);
}
