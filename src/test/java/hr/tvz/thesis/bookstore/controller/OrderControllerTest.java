package hr.tvz.thesis.bookstore.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.repository.BookRepository;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import hr.tvz.thesis.bookstore.service.JwtService;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
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
class OrderControllerTest {

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

  User helperUser;

  List<Book> helperBooks;

  Order mockOrder;


  @BeforeEach
  void setUp() {
    Optional<User> user = userRepository.findByUsername("mzaninovic");
    Optional<User> admin = userRepository.findByUsername("admin");

    userToken = jwtService.createJwtToken(user.get());
    adminToken = jwtService.createJwtToken(admin.get());

    helperUser = userRepository.findByUsername("mzaninovic").get();
    helperBooks = bookRepository.findAll();
    mockOrder = new Order(
        0L,
        LocalDateTime.now(),
        BigDecimal.TEN,
        helperUser,
        List.of(
            helperBooks.get(1),
            helperBooks.get(1),
            helperBooks.get(1),
            helperBooks.get(2),
            helperBooks.get(2),
            helperBooks.get(3),
            helperBooks.get(6),
            helperBooks.get(7),
            helperBooks.get(7)
        )
    );
  }

  @Test
  void getOrderById() throws Exception {
    this.mockMvc.perform(
            get("/orders/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void getOrderByIdUnauthenticated() throws Exception {
    this.mockMvc.perform(
            get("/orders/1")
                .with(csrf()))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void getOrderByNonExistentId() throws Exception {
    this.mockMvc.perform(
            get("/orders/1000001")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isNotFound());
  }

  @Test
  void getOrderByUserId() throws Exception {
    this.mockMvc.perform(
            get("/orders/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void saveOrderAdmin() throws Exception {
    this.mockMvc.perform(
            post("/api/orders/add")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockOrder))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void saveOrderUser() throws Exception {
    this.mockMvc.perform(
            post("/api/orders/add")
                .with(user("mzaninovic").password("mzaninovic").roles("user"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockOrder))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void saveOrderUnauthenticated() throws Exception {
    this.mockMvc.perform(
            post("/api/orders/add")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockOrder))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }
}