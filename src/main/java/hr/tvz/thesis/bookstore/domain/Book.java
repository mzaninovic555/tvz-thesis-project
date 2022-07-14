package hr.tvz.thesis.bookstore.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
  private Long id;

  private String format;

  @Column(name = "page_number")
  private Integer pageNumber;

  private String binding;

  private Double mass;

  private String barcode;

  private String title;

  private BigDecimal price;

  private String description;

  @Column(name = "publishing_year")
  private Integer publishingYear;

  private Integer stock;

  @Column(name = "is_discount")
  private Boolean isDiscount;

  private String isbn;

  @Column(name = "image_path")
  private String imagePath;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;

  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  @OneToOne
  private Discount discount;

  @ManyToOne
  @JoinColumn(name = "language_id")
  private Language language;

  @ManyToMany(targetEntity = Category.class, mappedBy = "books")
  private List<Category> categories;

  @ManyToMany(targetEntity = Order.class, mappedBy = "books")
  private List<Order> orders;
}
