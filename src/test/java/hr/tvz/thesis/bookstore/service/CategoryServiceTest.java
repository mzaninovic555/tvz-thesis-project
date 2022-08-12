package hr.tvz.thesis.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hr.tvz.thesis.bookstore.domain.Category;
import hr.tvz.thesis.bookstore.domain.dto.CategoryDTO;
import hr.tvz.thesis.bookstore.repository.CategoryRepository;
import java.util.List;
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
class CategoryServiceTest {

  @Autowired
  CategoryService categoryService;

  @Autowired
  CategoryRepository categoryRepository;

  private static List<Category> mockCategories;

  @BeforeEach
  void setUp() {
    mockCategories = categoryRepository.findAll();
  }

  @Test
  void getAllTest() {
    List<CategoryDTO> allCategories = categoryService.getAll();

    assertNotEquals(0, allCategories.size());
    assertTrue(
        allCategories
            .stream()
            .map(CategoryDTO::getId)
            .toList()
            .containsAll(mockCategories.stream().map(Category::getId).toList())
    );
  }

  @Test
  void getByFirstCategoryIdTest() {
    Optional<CategoryDTO> foundByFirstId = categoryService.getById(mockCategories.get(0).getId());
    Category categoryToFind = mockCategories.get(0);

    assertTrue(foundByFirstId.isPresent());
    assertEquals(foundByFirstId.get().getId(), categoryToFind.getId());
    assertEquals(foundByFirstId.get().getName(), categoryToFind.getName());
  }

  @Test
  void getByNonexistentIdTest() {
    Optional<CategoryDTO> foundByFirstId = categoryService.getById(-123L);

    assertFalse(foundByFirstId.isPresent());
  }

  @Test
  void namesaveCategoryTest() {
    Category mockCategory = new Category(
        0L,
        "testCategory",
        null
    );

    CategoryDTO mockCategoryDTO = categoryService.save(mockCategory);
    Optional<Category> savedCategory = categoryRepository.findById(mockCategoryDTO.getId());

    assertTrue(savedCategory.isPresent());
    assertEquals(savedCategory.get().getId(), mockCategoryDTO.getId());
    assertEquals(savedCategory.get().getName(), mockCategoryDTO.getName());

  }
}