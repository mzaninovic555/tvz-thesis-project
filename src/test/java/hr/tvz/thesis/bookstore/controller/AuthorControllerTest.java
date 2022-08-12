package hr.tvz.thesis.bookstore.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.thesis.bookstore.domain.Author;
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
class AuthorControllerTest {

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

  Author mockAuthor = new Author(
      0L,
      "Test",
      "Tester",
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
  void getAllAuthorsTest() throws Exception {
    this.mockMvc.perform(
        get("/author/all")
            .with(user("admin").password("admin").roles("admin"))
            .with(csrf())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void getAllAuthorsNotLoggedInTest() throws Exception {
    this.mockMvc.perform(
            get("/author/all")
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void getAuthorById() throws Exception {
    this.mockMvc.perform(
            get("/author/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void getAuthorByNonExistentId() throws Exception {
    this.mockMvc.perform(
            get("/author/1000001")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isNotFound());
  }

  @Test
  void saveAuthorAdmin() throws Exception {
    this.mockMvc.perform(
        post("/api/add/author/")
            .with(user("admin").password("admin").roles("admin"))
            .with(csrf())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(mockAuthor))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8))
        .andExpect(jsonPath("$.firstName").value("Test"))
        .andExpect(jsonPath("$.lastName").value("Tester"));
  }

  @Test
  void saveAuthorUser() throws Exception {
    this.mockMvc.perform(
            post("/api/add/author/")
                .with(user("mzaninovic").password("mzaninovic").roles("user"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockAuthor))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
  }
}