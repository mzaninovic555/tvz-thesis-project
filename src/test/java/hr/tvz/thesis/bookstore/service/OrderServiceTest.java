package hr.tvz.thesis.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;
import hr.tvz.thesis.bookstore.repository.BookRepository;
import hr.tvz.thesis.bookstore.repository.OrderRepository;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class OrderServiceTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  BookRepository bookRepository;

  private static Order mockOrder;

  private static List<Book> mockBooks;

  private static User mockUser;

  @BeforeEach
  void setUp() {
    mockUser = userRepository.findByUsername("mzaninovic").get();
    mockOrder = orderRepository.findById(1L).get();
    mockBooks = bookRepository.findAll();
  }

  @Test
  void namegetByUserIdTest() {
    List<OrderDTO> foundByUserId = orderService.getByUserId(mockUser.getId());

    assertNotEquals(0, foundByUserId.size());
    assertEquals(mockOrder.getId(), foundByUserId.get(0).getId());
    assertTrue(
        mockOrder.getBooks()
            .stream()
            .map(Book::getId)
            .toList().containsAll(foundByUserId.get(0).getBooks().stream().map(BookDTO::getId).toList())
    );
  }

  @Test
  void getByMockId() {
    Optional<OrderDTO> foundByFirstId = orderService.getById(mockOrder.getId());

    assertTrue(foundByFirstId.isPresent());
    assertEquals(foundByFirstId.get().getId(), mockOrder.getId());
    assertTrue(
        mockOrder.getBooks()
            .stream()
            .map(Book::getId)
            .toList().containsAll(foundByFirstId.get().getBooks().stream().map(BookDTO::getId).toList())
    );
  }

  @Test
  void getByNonExistentId() {
    Optional<OrderDTO> foundByFirstId = orderService.getById(-123L);

    assertFalse(foundByFirstId.isPresent());
  }

  @Test
  void save() throws MessagingException {
    Order mockOrder = new Order(
        0L,
        LocalDateTime.now(),
        BigDecimal.TEN,
        mockUser,
        List.of(
            mockBooks.get(0),
            mockBooks.get(0),
            mockBooks.get(0),
            mockBooks.get(1),
            mockBooks.get(1),
            mockBooks.get(3)
        )
    );

    OrderDTO order = orderService.save(mockOrder, false);
    Optional<Order> mockOrderFind = orderRepository.findById(order.getId());

    assertTrue(mockOrderFind.isPresent());
    assertEquals(mockOrderFind.get().getId(), order.getId());
    assertTrue(
        mockOrderFind.get().getBooks()
            .stream()
            .map(Book::getId)
            .toList().containsAll(order.getBooks().stream().map(BookDTO::getId).toList())
    );
  }
}
