package hr.tvz.thesis.bookstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  private final Map<String, Object> bodyAdmin = Map.of(
      "username", "admin",
      "password", "admin"
  );

  private final Map<String, Object> bodyUser = Map.of(
      "username", "mzaninovic",
      "password", "mzaninovic"
  );

  private final Map<String, Object> bodyWrongLogin = Map.of(
      "username", "krivo",
      "password", "fakat krivo"
  );

  @Test
  void login_admin() throws Exception {
    this.mockMvc.perform(
            post("/authentication/login").secure(true)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bodyAdmin)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8))
        .andExpect(jsonPath("$.jwt").isNotEmpty());
  }

  @Test
  void login_user() throws Exception {
    this.mockMvc.perform(
            post("/authentication/login").secure(true)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bodyUser)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().encoding(StandardCharsets.UTF_8))
        .andExpect(jsonPath("$.jwt").isNotEmpty());
  }

  @Test
  void login_wrongLoginInfo() throws Exception {
    this.mockMvc.perform(
            post("/authentication/login").secure(true)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bodyWrongLogin)))
        .andExpect(status().isBadRequest());
  }
}