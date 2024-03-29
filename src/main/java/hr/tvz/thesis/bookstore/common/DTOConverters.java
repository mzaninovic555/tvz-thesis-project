package hr.tvz.thesis.bookstore.common;

import hr.tvz.thesis.bookstore.domain.Author;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Category;
import hr.tvz.thesis.bookstore.domain.Discount;
import hr.tvz.thesis.bookstore.domain.Language;
import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.Publisher;
import hr.tvz.thesis.bookstore.domain.Review;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.AuthorDTO;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.domain.dto.CategoryDTO;
import hr.tvz.thesis.bookstore.domain.dto.DiscountDTO;
import hr.tvz.thesis.bookstore.domain.dto.LanguageDTO;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;
import hr.tvz.thesis.bookstore.domain.dto.PublisherDTO;
import hr.tvz.thesis.bookstore.domain.dto.ReviewDTO;
import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DTOConverters {

  /**
   * Converts {@link Book} object to {@link BookDTO}.
   * @param book book to be converted
   * @return returns converted BookDTO object
   */
  public static BookDTO mapBookToBookDTO(Book book) {
    return BookDTO.builder()
        .id(book.getId())
        .format(book.getFormat())
        .pageNumber(book.getPageNumber())
        .binding(book.getBinding())
        .mass(book.getMass())
        .barcode(book.getBarcode())
        .title(book.getTitle())
        .price(book.getPrice())
        .discountPrice(book.getDiscount() != null
            && !book.getDiscount().getStartedAt().isAfter(LocalDate.now())
            && !book.getDiscount().getEndsAt().isBefore(LocalDate.now())
            ? book.getDiscount().getDiscountPrice() : BigDecimal.ZERO)
        .discountExpiration((book.getDiscount() != null ? book.getDiscount().getEndsAt() : null))
        .description(book.getDescription())
        .publishingYear(book.getPublishingYear())
        .stock(book.getStock())
        .isbn(book.getIsbn())
        .imagePath(book.getImagePath())
        .dateAdded(book.getDateAdded())
        .author(DTOConverters.mapAuthorToAuthorDTO(book.getAuthor()))
        .publisher(DTOConverters.mapPublisherToPublisherDTO(book.getPublisher()))
        .language(DTOConverters.mapLanguageToLanguageDTO(book.getLanguage()))
        .categories(DTOConverters.mapCategoriesToCategoriesDTO(book.getCategories()))
        .reviews(DTOConverters.mapReviewsToReviewsDTO(book.getReviews()))
        .build();
  }

  public static List<BookDTO> mapBooksToBooksDTO(List<Book> books) {
    return books != null
        ? books
          .stream()
          .map(DTOConverters::mapBookToBookDTO)
          .toList() : null;
  }

  public static PublisherDTO mapPublisherToPublisherDTO(Publisher publisher) {
    return PublisherDTO.builder()
        .id(publisher.getId())
        .name(publisher.getName())
        .build();
  }

  public static AuthorDTO mapAuthorToAuthorDTO(Author author) {
    return AuthorDTO.builder()
        .id(author.getId())
        .firstName(author.getFirstName())
        .lastName(author.getLastName())
        .build();
  }

  public static List<CategoryDTO> mapCategoriesToCategoriesDTO(List<Category> categories) {
    return categories != null
        ? categories
          .stream()
          .map(DTOConverters::mapCategoryToCategoryDTO)
          .toList() : List.of();
  }

  public static CategoryDTO mapCategoryToCategoryDTO(Category category) {
    return CategoryDTO.builder()
        .id(category.getId())
        .name(category.getName())
        .build();
  }

  public static OrderDTO mapOrderToOrderDTO(Order order) {
    return OrderDTO.builder()
        .id(order.getId())
        .datePlaced(order.getDatePlaced())
        .totalPrice(order.getTotalPrice())
        .user(mapUserToUserDTO(order.getUser()))
        .books(mapBooksToBooksDTO(order.getBooks()))
        .build();
  }

  public static UserDTO mapUserToUserDTO(User user) {
    return UserDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .phoneNumber(user.getPhoneNumber())
        .address(user.getAddress())
        .postalCode(user.getPostalCode())
        .city(user.getCity())
        .build();
  }

  public static LanguageDTO mapLanguageToLanguageDTO(Language language) {
    return LanguageDTO.builder()
        .id(language.getId())
        .name(language.getName())
        .build();
  }

  public static DiscountDTO mapDiscountTODiscountDTO(Discount discount) {
    return DiscountDTO.builder()
        .id(discount.getId())
        .discountPrice(discount.getDiscountPrice())
        .startedAt(discount.getStartedAt())
        .endsAt(discount.getEndsAt())
        .build();
  }

  public static ReviewDTO mapReviewToReviewDTO(Review review) {
    return ReviewDTO.builder()
        .id(review.getId())
        .score(review.getScore())
        .user(DTOConverters.mapUserToUserDTO(review.getUser()))
        .build();
  }

  public static List<ReviewDTO> mapReviewsToReviewsDTO(List<Review> reviews) {
    return reviews != null
        ? reviews
          .stream()
          .map(DTOConverters::mapReviewToReviewDTO)
          .toList() : List.of();
  }
}
