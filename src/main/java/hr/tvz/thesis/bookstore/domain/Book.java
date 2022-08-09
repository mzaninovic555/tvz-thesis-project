package hr.tvz.thesis.bookstore.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String format;

  @Column(name = "page_number")
  @NotNull
  private Integer pageNumber;

  @NotNull
  private String binding;

  @NotNull
  private Double mass;

  @NotNull
  @Pattern(regexp = "\\d{11}")
  private String barcode;

  @NotNull
  private String title;

  @NotNull
  @Max(10000)
  @Positive
  private BigDecimal price;

  @NotNull
  private String description;

  @Column(name = "publishing_year")
  @NotNull
  private Integer publishingYear;

  @NotNull
  private Integer stock;

  @NotNull
  private String isbn;

  @Column(name = "image_path")
  @NotNull
  private String imagePath;

  @Column(name = "date_added")
  private LocalDate dateAdded;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;

  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  @OneToOne(mappedBy = "book", fetch = FetchType.EAGER)
  private Discount discount;

  @ManyToOne
  @JoinColumn(name = "language_id")
  private Language language;

  @ManyToMany(mappedBy = "books")
  private List<Category> categories;

  @ManyToMany(mappedBy = "books")
  private List<Order> orders;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Book book = (Book) o;

    return id.equals(book.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
