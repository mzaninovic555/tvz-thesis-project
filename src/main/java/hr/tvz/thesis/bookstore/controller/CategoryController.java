package hr.tvz.thesis.bookstore.controller;


import hr.tvz.thesis.bookstore.domain.dto.CategoryDTO;
import hr.tvz.thesis.bookstore.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
    return categoryService.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
