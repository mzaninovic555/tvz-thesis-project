package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.dto.CategoryDTO;
import hr.tvz.thesis.bookstore.repository.CategoryRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Optional<CategoryDTO> getById(Long id) {
    return categoryRepository.findById(id).map(DTOConverters::mapCategoryToCategoryDTO);
  }
}
