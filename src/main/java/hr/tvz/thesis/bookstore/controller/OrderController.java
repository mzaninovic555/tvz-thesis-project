package hr.tvz.thesis.bookstore.controller;

import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;
import hr.tvz.thesis.bookstore.service.OrderService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping ("/api/orders/add")
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public OrderDTO save(@RequestBody final Order order) {
    return orderService.save(order);
  }
}