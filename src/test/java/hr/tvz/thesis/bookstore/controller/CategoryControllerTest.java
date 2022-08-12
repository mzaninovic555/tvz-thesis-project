package hr.tvz.thesis.bookstore.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.thesis.bookstore.domain.Category;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import hr.tvz.thesis.bookstore.service.JwtService;
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
class CategoryControllerTest {

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

  Category mockCategory = new Category(
      0L,
      "nova kategorija",
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
  void getAllCategoriesTest() throws Exception {
    this.mockMvc.perform(
            get("/category")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void getAllCategoriesNotLoggedInTest() throws Exception {
    this.mockMvc.perform(
            get("/category")
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void getCategoryById() throws Exception {
    this.mockMvc.perform(
            get("/category/1")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  void getCategoryByNonExistentId() throws Exception {
    this.mockMvc.perform(
            get("/category/1000001")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isNotFound());
  }

  @Test
  void saveCategoryAdmin() throws Exception {
    this.mockMvc.perform(
            post("/api/add/category/")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockCategory))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name").value("nova kategorija"));
  }

  @Test
  void saveCategoryUser() throws Exception {
    this.mockMvc.perform(
            post("/api/add/category/")
                .with(user("mzaninovic").password("mzaninovic").roles("user"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockCategory))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
  }
}