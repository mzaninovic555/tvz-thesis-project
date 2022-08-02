package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;
import hr.tvz.thesis.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public OrderDTO save(Order order) {
    return DTOConverters.mapOrderToOrderDTO(orderRepository.save(order));
  }
}
