package hr.tvz.thesis.bookstore.controller;

import hr.tvz.thesis.bookstore.domain.Login;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.LoginDTO;
import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import hr.tvz.thesis.bookstore.service.LoginService;
import javax.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController {

  private final LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/authentication/login")
  public LoginDTO login(@RequestBody final Login login) {
    return loginService.login(login)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials"));
  }

  @PostMapping("/authentication/register")
  public ResponseEntity<UserDTO> register(@RequestBody @Valid final User user) {
    UserDTO registeredUser = loginService.register(user);

    if (registeredUser == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.ok(registeredUser);
  }
}
