package hr.tvz.thesis.bookstore.controller;

import hr.tvz.thesis.bookstore.common.Constants;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Discount;
import hr.tvz.thesis.bookstore.domain.Review;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.domain.dto.DiscountDTO;
import hr.tvz.thesis.bookstore.domain.dto.LanguageDTO;
import hr.tvz.thesis.bookstore.domain.dto.ReviewDTO;
import hr.tvz.thesis.bookstore.service.BookService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("books")
  public List<BookDTO> findAll() {
    return bookService.findAll();
  }

  @GetMapping("books/{id}")
  public ResponseEntity<BookDTO> findById(@PathVariable final Long id) {
    return bookService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("api/books/author/{id}")
  public List<BookDTO> findByAuthorId(@PathVariable final Long id) {
    return bookService.findByAuthorId(id);
  }

  @GetMapping("api/books/publisher/{id}")
  public List<BookDTO> findByPublisherId(@PathVariable final Long id) {
    return bookService.findByPublisherId(id);
  }

  @GetMapping("/api/books/search-by-title/{searchTerm}")
  public List<BookDTO> findByTitle(@PathVariable final String searchTerm) {
    return bookService.findByTitle(searchTerm);
  }

  @GetMapping("/api/books/category/{id}")
  public List<BookDTO> findByCategoryId(@PathVariable final Long id) {
    return bookService.findByCategoryId(id);
  }

  @GetMapping("/api/books/order/{id}")
  public List<BookDTO> findByOrderId(@PathVariable final Long id) {
    return bookService.findByOrderId(id);
  }

  @GetMapping("/api/languages/all")
  public List<LanguageDTO> findAllLanguages() {
    return bookService.findAllLanguages();
  }

  @PostMapping("/api/add/book")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<BookDTO> save(@RequestBody Book book) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
  }

  @PostMapping("/api/add/book/image/{bookId}")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<BookDTO> save(@RequestParam("image") MultipartFile bookImage,
      @PathVariable Long bookId) throws IOException {

    String bookImageFileName = bookImage.getOriginalFilename();
    String destination = Constants.IMAGE_PATH + bookImageFileName;

    File bookImageFile = new File(destination);
    bookImage.transferTo(bookImageFile);
    bookService.saveBookImage(bookId, bookImageFileName);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/api/add/discount")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<DiscountDTO> save(@RequestBody @Valid Discount discount) {
    DiscountDTO newDiscount = bookService.saveDiscount(discount);

    if (newDiscount == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(newDiscount);
  }

  @PostMapping("/api/add/review")
  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  public ResponseEntity<ReviewDTO> saveReview(@RequestBody @Valid Review review) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveReview(review));
  }
}
