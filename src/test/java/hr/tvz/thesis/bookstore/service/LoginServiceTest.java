package hr.tvz.thesis.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hr.tvz.thesis.bookstore.domain.Login;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.LoginDTO;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class LoginServiceTest {

  @Autowired
  LoginService loginService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  JwtService jwtService;

  private static User testUser;

  @BeforeEach
  void setUp() {
    testUser = userRepository.findByUsername("mzaninovic").get();
  }



  @Test
  void nameloginTest() {
    Optional<LoginDTO> mockLogin = loginService.login(new Login(testUser.getUsername(), "mzaninovic"));

    assertTrue(mockLogin.isPresent());
    assertTrue(jwtService.authenticate(mockLogin.get().getJwt()));
  }

  @Test
  void loginWrongCredentialsTest() {
    Optional<LoginDTO> mockLogin = loginService.login(new Login(testUser.getUsername(), "nekiRandomPassword"));

    assertFalse(mockLogin.isPresent());
  }
}