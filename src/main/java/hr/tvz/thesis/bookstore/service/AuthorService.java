package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.dto.AuthorDTO;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

  List<AuthorDTO> getAllAuthors();

  Optional<AuthorDTO> getAuthorById(Long id);
}
