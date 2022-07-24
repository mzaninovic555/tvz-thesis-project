package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import java.util.List;
import java.util.Optional;

public interface BookService {

  List<BookDTO> findAll();

  Optional<BookDTO> findById(Long id);

  List<BookDTO> findByAuthorId(Long id);

  List<BookDTO> findByPublisherId(Long id);

  List<BookDTO> findByTitle(String searchTerm);
}
