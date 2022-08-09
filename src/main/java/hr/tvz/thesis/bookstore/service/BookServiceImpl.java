package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.domain.dto.LanguageDTO;
import hr.tvz.thesis.bookstore.repository.BookRepository;
import hr.tvz.thesis.bookstore.repository.LanguageRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final LanguageRepository languageRepository;

  public BookServiceImpl(BookRepository bookRepository, LanguageRepository languageRepository) {
    this.bookRepository = bookRepository;
    this.languageRepository = languageRepository;
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

  @Override
  public List<BookDTO> findByOrderId(Long id) {
    return bookRepository.findByOrdersId(id)
        .stream()
        .map(DTOConverters::mapBookToBookDTO)
        .toList();
  }

  @Override
  public List<LanguageDTO> findAllLanguages() {
    return languageRepository.findAll()
        .stream()
        .map(DTOConverters::mapLanguageToLanguageDTO)
        .toList();
  }

  @Override
  public BookDTO save(Book book) {

    book.setDiscount(null);
    book.setDateAdded(LocalDate.now());

    return DTOConverters.mapBookToBookDTO(bookRepository.save(book));
  }
}
