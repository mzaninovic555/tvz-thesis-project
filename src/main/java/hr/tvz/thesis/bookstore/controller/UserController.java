package hr.tvz.thesis.bookstore.controller;

import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import hr.tvz.thesis.bookstore.service.UserService;
import hr.tvz.thesis.bookstore.user.ApplicationUser;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/{username}")
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public ResponseEntity<UserDTO> finyByUsername(@PathVariable String username) {

    ApplicationUser loggedInUser = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (loggedInUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
        || loggedInUser.getUsername().equals(username)) {
      return userService.findByUsername(username)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @GetMapping("/user/all")
  public List<UserDTO> findAllUsers() {
    return userService.findAll();
  }
}
