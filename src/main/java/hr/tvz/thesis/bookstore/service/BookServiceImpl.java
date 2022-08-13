package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Discount;
import hr.tvz.thesis.bookstore.domain.Review;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.domain.dto.DiscountDTO;
import hr.tvz.thesis.bookstore.domain.dto.LanguageDTO;
import hr.tvz.thesis.bookstore.domain.dto.ReviewDTO;
import hr.tvz.thesis.bookstore.repository.BookRepository;
import hr.tvz.thesis.bookstore.repository.DiscountRepository;
import hr.tvz.thesis.bookstore.repository.LanguageRepository;
import hr.tvz.thesis.bookstore.repository.ReviewRepository;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final LanguageRepository languageRepository;
  private final DiscountRepository discountRepository;
  private final ReviewRepository reviewRepository;

  public BookServiceImpl(BookRepository bookRepository, LanguageRepository languageRepository,
      DiscountRepository discountRepository, ReviewRepository reviewRepository) {
    this.bookRepository = bookRepository;
    this.languageRepository = languageRepository;
    this.discountRepository = discountRepository;
    this.reviewRepository = reviewRepository;
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
    return bookRepository.findById(id).map(DTOConverters::mapBookToBookDTO);
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

  @Override
  public void saveBookImage(Long bookId, String bookImage) {
    bookRepository.saveBookImage(bookId, bookImage);
  }

  @Override
  public DiscountDTO saveDiscount(Discount discount) {
    if (discount.getDiscountPrice().compareTo(discount.getBook().getPrice()) >= 0
        || discount.getStartedAt().isAfter(discount.getEndsAt())) {
      return null;
    }
    return DTOConverters.mapDiscountTODiscountDTO(discountRepository.save(discount));
  }

  @Override
  public ReviewDTO saveReview(Review review) {
    return DTOConverters.mapReviewToReviewDTO(reviewRepository.save(review));
  }

  @Override
  public BookDTO encodeImagePath(BookDTO bookDTO) {
    try {
      bookDTO.setImagePath(convertImageToBase64(bookDTO.getImagePath()));
    } catch (IOException ex) {
      log.error(ex);
    }

    return bookDTO;
  }

  private String convertImageToBase64(String imagePath) throws IOException {
    File image = new File("C:\\Users\\mzani\\Desktop\\Thesis\\books\\" + imagePath);

    byte[] imageContent = FileUtils.readFileToByteArray(image);
    return Base64.getEncoder().encodeToString(imageContent);
  }
}
