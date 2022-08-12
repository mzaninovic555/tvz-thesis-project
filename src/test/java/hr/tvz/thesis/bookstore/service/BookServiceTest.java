package hr.tvz.thesis.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Language;
import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.Role;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.repository.BookRepository;
import hr.tvz.thesis.bookstore.repository.DiscountRepository;
import hr.tvz.thesis.bookstore.repository.OrderRepository;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class BookServiceTest {

  @Autowired
  BookService bookService;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  DiscountRepository discountRepository;


  List<Book> mockBooks;

  @BeforeEach
  void setUp() {
    mockBooks = bookRepository.findAll();
  }

  @Test
  void findAllTest() {
    List<BookDTO> allBooks = bookService.findAll();

    assertNotEquals(0, allBooks.size());
    assertTrue(
        allBooks
            .stream()
            .map(BookDTO::getId)
            .toList()
            .containsAll(mockBooks.stream().map(Book::getId).toList())
    );
  }

  @Test
  void findByFirstBookIdTest() {
    Optional<BookDTO> foundByFirstId = bookService.findById(mockBooks.get(0).getId());
    Book bookToFind = mockBooks.get(0);

    assertTrue(foundByFirstId.isPresent());
    assertEquals(foundByFirstId.get().getId(), bookToFind.getId());
    assertEquals(foundByFirstId.get().getTitle(), bookToFind.getTitle());
  }

  @Test
  void findByNonExistentIdTest() {
    Optional<BookDTO> foundByFirstId = bookService.findById(-123L);

    assertFalse(foundByFirstId.isPresent());
  }

  @Test
  void findBySecondBookAuthorIdTest() {
    List<BookDTO> foundBySecondBookAuthorId = bookService.findByAuthorId(mockBooks.get(0).getAuthor().getId());
    List<Book> bookToFind = mockBooks
        .stream()
        .filter(b -> b.getAuthor().getId().equals(mockBooks.get(0).getAuthor().getId()))
        .toList();

    assertNotEquals(0, foundBySecondBookAuthorId.size());
    assertTrue(
        foundBySecondBookAuthorId
            .stream()
            .map(b -> b.getAuthor().getId())
            .toList()
            .containsAll(bookToFind.stream().map(b -> b.getAuthor().getId()).toList())
    );
  }

  @Test
  void findByNonExistentAuthorIdTest() {
    List<BookDTO> foundBySecondBookAuthorId = bookService.findByAuthorId(-123L);

    assertEquals(0, foundBySecondBookAuthorId.size());
  }

  @Test
  void findBySecondBookPublisherIdTest() {
    List<BookDTO> foundBySecondBookPublisherId = bookService.findByPublisherId(mockBooks.get(0).getPublisher().getId());
    List<Book> bookToFind = mockBooks
        .stream()
        .filter(b -> b.getPublisher().getId().equals(mockBooks.get(0).getPublisher().getId()))
        .toList();

    assertNotEquals(0, foundBySecondBookPublisherId.size());
    assertTrue(
        foundBySecondBookPublisherId
            .stream()
            .map(b -> b.getPublisher().getId())
            .toList()
            .containsAll(bookToFind.stream().map(b -> b.getPublisher().getId()).toList())
    );
  }

  @Test
  void findByNonExistentPublisherIdTest() {
    List<BookDTO> foundBySecondBookPublisherId = bookService.findByPublisherId(-123L);

    assertEquals(0, foundBySecondBookPublisherId.size());
  }

  @Test
  void findByFirstBookTitleTest() {
    List<BookDTO> foundByFirstBookTitle = bookService.findByTitle(mockBooks.get(0).getTitle());
    List<Book> bookToFind = mockBooks
        .stream()
        .filter(b -> b.getTitle().equals(mockBooks.get(0).getTitle()))
        .toList();

    assertNotEquals(0, foundByFirstBookTitle.size());
    assertTrue(
        foundByFirstBookTitle
            .stream()
            .map(BookDTO::getId)
            .toList()
            .containsAll(bookToFind.stream().map(Book::getId).toList())
    );
  }

  @Test
  void findByNonExistentBookTitleTest() {
    List<BookDTO> foundByNonexistentBookTitle = bookService.findByTitle("Ne postoji ovaj naslov, vjeruj mi.");

    assertEquals(0, foundByNonexistentBookTitle.size());
  }

  @Test
  void findByFirstBookCategoryIdTest() {
    Optional<BookDTO> foundByFirstId = bookService.findById(mockBooks.get(0).getId());
    Book bookToFind = mockBooks.get(0);

    assertTrue(foundByFirstId.isPresent());
    assertEquals(foundByFirstId.get().getId(), bookToFind.getId());
    assertEquals(foundByFirstId.get().getTitle(), bookToFind.getTitle());
  }

  @Test
  void updateQuntityToReduceTest() {
    Integer quantityToRecude = mockBooks.get(0).getStock();
    bookService.updateStock(mockBooks.get(0).getId(), quantityToRecude);

    assertEquals(0, bookRepository.findById(mockBooks.get(0).getId()).get().getStock() - quantityToRecude);
  }

  @Test
  void findByOrderIdTest() {
    User mockUser = new User(0L,
        "username hahaha",
        "passwwwwword",
        "email@email.com",
        "name",
        "lastname",
        "+3850997976589",
        "adresa",
        "10000",
        "zagreb",
        null,
        null,
        Set.of(new Role(2L, "ROLE_USER")));
    mockUser = userRepository.save(mockUser);

    Order mockOrder = new Order(0L, LocalDateTime.now(), BigDecimal.TEN, mockUser, List.of(mockBooks.get(0), mockBooks.get(1)));
    mockOrder = orderRepository.save(mockOrder);

    List<BookDTO> foundByOrder = bookService.findByOrderId(mockOrder.getId());

    assertNotEquals(0, foundByOrder.size());
    assertTrue(
        foundByOrder
            .stream()
            .map(BookDTO::getId)
            .toList()
            .containsAll(List.of(mockBooks.get(0).getId(), mockBooks.get(1).getId()))
    );
  }

  @Test
  void saveTest() {
    Book mockBook = new Book(
        0L,
        "2x1",
        190,
        "Tvrdi",
        1.9,
        "11111111111",
        "Testna knjiga",
        BigDecimal.TEN,
        "testni opis",
        2020,
        20,
        "11111111111",
        "nema",
        LocalDate.now(),
        mockBooks.get(0).getAuthor(),
        mockBooks.get(0).getPublisher(),
        null,
        new Language(1L, "Hrvatski", null),
        mockBooks.get(0).getCategories(),
        null,
        null
    );

    BookDTO mockBookDTO = bookService.save(mockBook);
    Optional<Book> testSaveBook = bookRepository.findById(mockBookDTO.getId());
    assertTrue(testSaveBook.isPresent());
    assertEquals(mockBookDTO.getId(), testSaveBook.get().getId());
    assertEquals(mockBookDTO.getTitle(), testSaveBook.get().getTitle());
  }
}