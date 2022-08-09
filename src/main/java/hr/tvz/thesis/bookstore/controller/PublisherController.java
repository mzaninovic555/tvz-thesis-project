package hr.tvz.thesis.bookstore.controller;

import hr.tvz.thesis.bookstore.domain.Publisher;
import hr.tvz.thesis.bookstore.domain.dto.PublisherDTO;
import hr.tvz.thesis.bookstore.service.PublisherService;
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
public class PublisherController {

  private final PublisherService publisherService;

  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping("publisher/all")
  public List<PublisherDTO> getAllPublishers() {
    return publisherService.getAllPublishers();
  }

  @GetMapping("publisher/{id}")
  public ResponseEntity<PublisherDTO> finyById(@PathVariable Long id) {
    return publisherService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/api/add/publisher")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<PublisherDTO> save(@RequestBody @Valid Publisher publisher) {
    return ResponseEntity.ok(publisherService.save(publisher));
  }
}
