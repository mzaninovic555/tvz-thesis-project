package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
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

  @Override
  public List<BookDTO> findByAuthorId(Long id) {
    return bookRepository.findByAuthorId(id)
        .stream()
        .map(DTOConverters::mapBookToBookDTO)
        .toList();
  }

  @Override
  public List<BookDTO> findByPublisherId(Long id) {
    return bookRepository.findByPublisherId(id)
        .stream()
        .map(DTOConverters::mapBookToBookDTO)
        .toList();
  }

  @Override
  public List<BookDTO> findByTitle(String searchTerm) {
    return bookRepository.findByTitleContainsIgnoreCase(searchTerm)
        .stream()
        .map(DTOConverters::mapBookToBookDTO)
        .toList();
  }

  @Override
  public List<BookDTO> findByCategoryId(Long id) {
    return bookRepository.findByCategoriesId(id)
        .stream()
        .map(DTOConverters::mapBookToBookDTO)
        .toList();
  }

  @Override
  public int updateStock(Long bookId, Integer quantityToReduce) {
    return bookRepository.updateStock(bookId, quantityToReduce);
  }
}
