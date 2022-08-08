package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;
import hr.tvz.thesis.bookstore.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final BookService bookService;

  public OrderServiceImpl(OrderRepository orderRepository, BookService bookService) {
    this.orderRepository = orderRepository;
    this.bookService = bookService;
  }

  @Override
  public OrderDTO save(Order order) {
    List<Book> books = order.getBooks().stream().distinct().toList();
    List<Pair<Long, Integer>> bookIdQuantity = new ArrayList<>();

    for (Book book : books) {
      Integer quantity = Math.toIntExact(order.getBooks()
          .stream()
          .filter(b -> b.getId().equals(book.getId()))
          .count());
      bookIdQuantity.add(Pair.of(book.getId(), quantity));
    }

    bookIdQuantity.forEach(bq ->
        bookService.updateStock(bq.getFirst(), bq.getSecond())
    );
    order.setDatePlaced(LocalDateTime.now());
    return DTOConverters.mapOrderToOrderDTO(orderRepository.save(order));
  }

  @Override
  public List<OrderDTO> getByUserId(Long userId) {
    return orderRepository.getByUserId(userId)
        .stream()
        .map(DTOConverters::mapOrderToOrderDTO)
        .toList();
  }

  @Override
  public Optional<OrderDTO> getById(Long id) {
    return orderRepository.findById(id).map(DTOConverters::mapOrderToOrderDTO);
  }
}
