package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.dto.AuthorDTO;
import hr.tvz.thesis.bookstore.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public List<AuthorDTO> getAllAuthors() {
    return authorRepository.findAll()
        .stream()
        .map(DTOConverters::mapAuthorToAuthorDTO)
        .toList();
  }

  @Override
  public Optional<AuthorDTO> getAuthorById(Long id) {
    return authorRepository.findById(id).map(DTOConverters::mapAuthorToAuthorDTO);
  }
}
