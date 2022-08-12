package hr.tvz.thesis.bookstore.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Discount;
import hr.tvz.thesis.bookstore.domain.Language;
import hr.tvz.thesis.bookstore.domain.Review;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.repository.BookRepository;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import hr.tvz.thesis.bookstore.service.JwtService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  UserRepository userRepository;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  JwtService jwtService;

  private String userToken;

  private String adminToken;

  Book helpBook;

  Book mockBook;

  @BeforeEach
  void setUp() {
    Optional<User> user = userRepository.findByUsername("mzaninovic");
    Optional<User> admin = userRepository.findByUsername("admin");

    userToken = jwtService.createJwtToken(user.get());
    adminToken = jwtService.createJwtToken(admin.get());

    helpBook = bookRepository.findById(1L).get();

     mockBook = new Book(
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
        helpBook.getAuthor(),
        helpBook.getPublisher(),
        null,
        new Language(1L, "Hrvatski", null),
        helpBook.getCategories(),
        null,
        null
    );
  }

  @Test
  void findAll() throws Exception {
    this.mockMvc.perform(
            get("/books")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void getAllBooksNotLoggedInTest() throws Exception {
    this.mockMvc.perform(
            get("/books")
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void findById() throws Exception {
    this.mockMvc.perform(
            get("/books/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void findByNonExistentId() throws Exception {
    this.mockMvc.perform(
            get("/books/12223131")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isNotFound());
  }

  @Test
  void findByAuthorId() throws Exception {
    this.mockMvc.perform(
            get("/api/books/author/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void findByPublisherId() throws Exception {
    this.mockMvc.perform(
            get("/api/books/publisher/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void findByTitle() throws Exception {
    this.mockMvc.perform(
            get("/api/books/search-by-title/Witcher")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void findByCategoryId() throws Exception {
    this.mockMvc.perform(
            get("/api/books/category/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void findByOrderId() throws Exception {
    this.mockMvc.perform(
            get("/api/books/order/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void findAllLanguages() throws Exception {
    this.mockMvc.perform(
            get("/api/languages/all")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void saveAdmin() throws Exception {
    this.mockMvc.perform(
            post("/api/add/book")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockBook))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.title").value("Testna knjiga"))
        .andExpect(jsonPath("$.description").value("testni opis"))
        .andExpect(jsonPath("$.price").value("10"));
  }

  @Test
  void saveUser() throws Exception {
    this.mockMvc.perform(
            post("/api/add/book")
                .with(user("mzaninovic").password("mzaninovic").roles("user"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockBook))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
  }

  @Test
  void saveDiscountAdmin() throws Exception {
    Discount discount = new Discount(
        0L,
        BigDecimal.TEN,
        LocalDate.now(),
        LocalDate.now().plusMonths(1),
        helpBook
    );

    this.mockMvc.perform(
            post("/api/add/discount")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(discount))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.discountPrice").value("10"));
  }

  @Test
  void saveReviewUser() throws Exception {
    Review review = new Review(
        0L,
        4,
        helpBook,
        userRepository.findByUsername("mzaninovic").get()
    );

    this.mockMvc.perform(
            post("/api/add/review")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(review))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.score").value("4"));
  }

  @Test
  void saveReviewNotLoggedIn() throws Exception {
    Review review = new Review(
        0L,
        2,
        helpBook,
        userRepository.findByUsername("admin").get()
    );

    this.mockMvc.perform(
            post("/api/add/review")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(review))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }
}