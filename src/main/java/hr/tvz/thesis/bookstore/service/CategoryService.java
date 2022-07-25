package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.dto.CategoryDTO;
import java.util.Optional;

public interface CategoryService {

  Optional<CategoryDTO> getById(Long id);
}
