package hr.tvz.thesis.bookstore.controller;

import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;
import hr.tvz.thesis.bookstore.service.OrderService;
import java.util.List;
import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/orders/{id}")
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public ResponseEntity<OrderDTO> getById(@PathVariable final Long id) {
    return orderService.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/api/orders/user/{userId}")
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public List<OrderDTO> getByUserId(@PathVariable final Long userId) {
    return orderService.getByUserId(userId);
  }

  @PostMapping ("/api/orders/add")
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public ResponseEntity<OrderDTO> save(@RequestBody @Valid final Order order) {
    if (order.getBooks().isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order, true));
    } catch (MessagingException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
