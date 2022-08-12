package hr.tvz.thesis.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  @NotBlank
  private String binding;

  @NotNull
  private Double mass;

  @NotNull
  @NotBlank
  @Pattern(regexp = "\\d{11}")
  private String barcode;

  @NotNull
  @NotBlank
  private String title;

  @NotNull
  @Max(10000)
  @Positive
  private BigDecimal price;

  @NotNull
  @NotBlank
  private String description;

  @Column(name = "publishing_year")
  @NotNull
  @Min(1900)
  @Max(2022)
  private Integer publishingYear;

  @NotNull
  @PositiveOrZero
  private Integer stock;

  @NotNull
  @NotBlank
  @Pattern(regexp = "\\d{11}")
  private String isbn;

  @Column(name = "image_path")
  @NotNull
  @NotBlank
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
  @JsonIgnore
  private Discount discount;

  @ManyToOne
  @JoinColumn(name = "language_id")
  private Language language;

  @ManyToMany(mappedBy = "books")
  private List<Category> categories;

  @ManyToMany(mappedBy = "books")
  @JsonIgnore
  private List<Order> orders;

  @OneToMany(mappedBy = "book")
  @JsonIgnore
  private List<Review> reviews;

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
