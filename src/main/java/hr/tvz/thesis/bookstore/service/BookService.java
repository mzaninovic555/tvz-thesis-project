package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import java.util.List;
import java.util.Optional;

public interface BookService {

  List<BookDTO> findAll();

  Optional<BookDTO> findById(Long id);
}