package hr.tvz.thesis.bookstore.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  UserRepository userRepository;

  @Autowired
  JwtService jwtService;

  private String adminToken;

  @BeforeEach
  void setUp() {
    Optional<User> admin = userRepository.findByUsername("admin");
    adminToken = jwtService.createJwtToken(admin.get());
  }

  @Test
  void getAllUsersTest() throws Exception {
    this.mockMvc.perform(
            get("/user/all")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void getUserByUsername() throws Exception {
    this.mockMvc.perform(
            get("/api/user/admin")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8));
  }

  @Test
  void getUserByNonExistentUsername() throws Exception {
    this.mockMvc.perform(
            get("/api/user/nepostojim")
                .with(user("admin").password("admin").roles("admin"))
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
        .andExpect(status().isNotFound());
  }

  @Test
  void getUserByUsernameUnauthenticated() throws Exception {
    this.mockMvc.perform(
            get("/api/user/admin")
                .with(csrf()))
        .andExpect(status().isUnauthorized());
  }
}