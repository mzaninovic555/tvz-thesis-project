package hr.tvz.thesis.bookstore.service;

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
        .map(this::mapBookToBookDTO)
        .toList();
  }

  @Override
  public Optional<BookDTO> findById(Long id) {
    return bookRepository.findById(id)
        .map(this::mapBookToBookDTO);
  }

  /**
   * Converts {@link Book} object to {@link BookDTO}.
   * @param book book to be converted
   * @return returns converted BookDTO object
   */
  public BookDTO mapBookToBookDTO(Book book) {
    return BookDTO.builder()
        .id(book.getId())
        .format(book.getFormat())
        .pageNumber(book.getPageNumber())
        .binding(book.getBinding())
        .mass(book.getMass())
        .barcode(book.getBarcode())
        .title(book.getTitle())
        .price(book.getPrice())
        .description(book.getDescription())
        .publishingYear(book.getPublishingYear())
        .stock(book.getStock())
        .isDiscount(book.getIsDiscount())
        .isbn(book.getIsbn())
        .imagePath(book.getImagePath())
        .author(book.getAuthor())
        .publisher(book.getPublisher())
        .categories(book.getCategories())
        .build();
  }
}
