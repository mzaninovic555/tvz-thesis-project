package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.dto.AuthorDTO;
import java.util.Optional;

public interface AuthorService {

  Optional<AuthorDTO> getAuthorById(Long id);
}
