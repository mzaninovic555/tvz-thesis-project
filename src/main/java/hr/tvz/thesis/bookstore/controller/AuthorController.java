package hr.tvz.thesis.bookstore.controller;

import hr.tvz.thesis.bookstore.domain.Author;
import hr.tvz.thesis.bookstore.domain.dto.AuthorDTO;
import hr.tvz.thesis.bookstore.service.AuthorService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("author/all")
  public List<AuthorDTO> getAllAuthors() {
    return authorService.getAllAuthors();
  }

  @GetMapping("author/{id}")
  public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
    return authorService.getAuthorById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/api/add/author")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<AuthorDTO> save(@RequestBody @Valid Author author) {
    return ResponseEntity.ok(authorService.save(author));
  }
}
