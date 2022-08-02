package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Order save(Order order) {
    return orderRepository.save(order);
  }
}
