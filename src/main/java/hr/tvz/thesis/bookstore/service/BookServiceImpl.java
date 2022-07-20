package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public List<BookDTO> findAll() {
    return bookRepository.findAll()
        .stream()
        .map(DTOConverters::mapBookToBookDTO)
        .toList();
  }

  @Override
  public Optional<BookDTO> findById(Long id) {
    return bookRepository.findById(id)
        .map(DTOConverters::mapBookToBookDTO);
  }
}
