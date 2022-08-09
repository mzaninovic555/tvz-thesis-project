package hr.tvz.thesis.bookstore.controller;


import hr.tvz.thesis.bookstore.domain.Category;
import hr.tvz.thesis.bookstore.domain.dto.CategoryDTO;
import hr.tvz.thesis.bookstore.service.CategoryService;
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
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("category")
  public List<CategoryDTO> findAll() {
    return categoryService.getAll();
  }

  @GetMapping("category/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
    return categoryService.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("api/add/category")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<CategoryDTO> save(@RequestBody @Valid Category category) {
    return ResponseEntity.ok(categoryService.save(category));
  }
}
