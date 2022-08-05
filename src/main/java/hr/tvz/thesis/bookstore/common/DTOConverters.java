package hr.tvz.thesis.bookstore.common;

import hr.tvz.thesis.bookstore.domain.Author;
import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Category;
import hr.tvz.thesis.bookstore.domain.Order;
import hr.tvz.thesis.bookstore.domain.Publisher;
import hr.tvz.thesis.bookstore.domain.dto.AuthorDTO;
import hr.tvz.thesis.bookstore.domain.dto.BookDTO;
import hr.tvz.thesis.bookstore.domain.dto.CategoryDTO;
import hr.tvz.thesis.bookstore.domain.dto.OrderDTO;
import hr.tvz.thesis.bookstore.domain.dto.PublisherDTO;
import java.math.BigDecimal;
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
        .discountPrice(book.getDiscount() != null ? book.getDiscount().getDiscountPrice() : BigDecimal.ZERO)
        .discountExpiration((book.getDiscount() != null ? book.getDiscount().getEndsAt() : null))
        .description(book.getDescription())
        .publishingYear(book.getPublishingYear())
        .stock(book.getStock())
        .isbn(book.getIsbn())
        .imagePath(book.getImagePath())
        .dateAdded(book.getDateAdded())
        .author(DTOConverters.mapAuthorToAuthorDTO(book.getAuthor()))
        .publisher(DTOConverters.mapPublisherToPublisherDTO(book.getPublisher()))
        .categories(DTOConverters.mapCategoriesToCategoriesDTO(book.getCategories()))
        .build();
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
    return categories
        .stream()
        .map(c -> CategoryDTO.builder()
            .id(c.getId())
            .name(c.getName())
            .build()
        ).toList();
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
        .build();
  }
}
