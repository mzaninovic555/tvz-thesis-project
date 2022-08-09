package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.domain.dto.LanguageDTO;
import java.util.List;
import java.util.Optional;

public interface BookService {

  List<BookDTO> findAll();

  Optional<BookDTO> findById(Long id);

  List<BookDTO> findByAuthorId(Long id);

  List<BookDTO> findByPublisherId(Long id);

  List<BookDTO> findByTitle(String searchTerm);

  List<BookDTO> findByCategoryId(Long id);

  int updateStock(Long bookId, Integer quantityToReduce);

  List<BookDTO> findByOrderId(Long id);

  List<LanguageDTO> findAllLanguages();

  BookDTO save(Book book);
}
