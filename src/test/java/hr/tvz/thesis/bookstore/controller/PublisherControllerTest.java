package hr.tvz.thesis.bookstore.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.thesis.bookstore.domain.Publisher;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import hr.tvz.thesis.bookstore.service.JwtService;
import java.nio.charset.StandardCharsets;
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
class PublisherControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  UserRepository userRepository;

  @Autowired
  JwtService jwtService;

  private String userToken;

  private String adminToken;

  Publisher mockPublisher = new Publisher(
      0L,
      "Test publisher",
      null
  );

  @BeforeEach
  void setUp() {
    Optional<User> user = userRepository.findByUsername("mzaninovic");
    Optional<User> admin = userRepository.findByUsername("admin");

    userToken = jwtService.createJwtToken(user.get());
    adminToken = jwtService.createJwtToken(admin.get());
  }

  @Test
  void getAllPublishersTest() throws Exception {
    this.mockMvc.perform(
            get("/publisher/all").secure(true)
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void getpublisherById() throws Exception {
    this.mockMvc.perform(
            get("/publisher/1").secure(true)
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void getPublisherByNonExistentId() throws Exception {
    this.mockMvc.perform(
            get("/publisher/1000001").secure(true)
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isNotFound());
  }

  @Test
  void savePublisherAdmin() throws Exception {
    this.mockMvc.perform(
            post("/api/add/publisher/").secure(true)
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockPublisher))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8))
        .andExpect(jsonPath("$.name").value("Test publisher"));
  }

  @Test
  void savePublisherUser() throws Exception {
    this.mockMvc.perform(
            post("/api/add/publisher/").secure(true)
                .with(user("mzaninovic").password("mzaninovic").roles("user"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockPublisher))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
  }
}