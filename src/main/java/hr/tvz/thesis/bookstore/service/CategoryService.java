package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Category;
import hr.tvz.thesis.bookstore.domain.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

  List<CategoryDTO> getAll();

  Optional<CategoryDTO> getById(Long id);

  CategoryDTO save(Category category);
}
